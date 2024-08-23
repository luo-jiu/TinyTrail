package org.tinytrail.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.tinytrail.admin.common.constant.RedisCacheConstant;
import org.tinytrail.admin.common.convention.exception.ClientException;
import org.tinytrail.admin.common.enums.UserErrorCodeEnum;
import org.tinytrail.admin.dao.entity.UserDO;
import org.tinytrail.admin.dao.mapper.UserMapper;
import org.tinytrail.admin.dto.req.UserLoginReqDTO;
import org.tinytrail.admin.dto.req.UserRegisterReqDTO;
import org.tinytrail.admin.dto.req.UserUpdateReqDTO;
import org.tinytrail.admin.dto.resp.UserLoginRespDTO;
import org.tinytrail.admin.dto.resp.UserRespDTO;
import org.tinytrail.admin.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        // 从Bloom过滤器里面去拿取
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (!hasUsername(requestParam.getUsername())) {
            // 用户名存在
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        }

        // 获取分布式锁(防止大量请求访问一个未注册的用户名)
        RLock lock = redissonClient.getLock(RedisCacheConstant.LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        try {
            if (lock.tryLock()) {
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                if (inserted < 1) {
                    // 用户新增失败
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                // 添加到Bloom过滤器
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        // TODO 验证当前用户是否为登录用户
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParam, UserDO.class), updateWrapper);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException("用户不存在");
        }
        Boolean hasLogin = stringRedisTemplate.hasKey("login_" + requestParam.getUsername());
        if (hasLogin != null && hasLogin) {
            throw new ClientException("用户已登录");
        }
        /**
         * Hash
         * Key: login_用户名
         * Value:
         *   key: token 标识
         *   value: JSON 字符串(用户信息)
         */
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(uuid, JSON.toJSONString(userDO), 30L, TimeUnit.MINUTES);

        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("token", JSON.toJSONString(userDO));

        stringRedisTemplate.opsForHash().put("login_" + requestParam.getUsername(), uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + requestParam.getUsername(), 30L, TimeUnit.MINUTES);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().get("login_" + username, token) != null;
    }

    @Override
    public void logout(String username, String token) {
        // 先验证是否登录
        if (checkLogin(username, token)) {
            stringRedisTemplate.delete("login_" + username);
            return;
        }
        throw new ClientException("用户Token 不存在或用户未登录");
    }
}
