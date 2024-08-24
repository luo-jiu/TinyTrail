package org.tinytrail.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;
import org.tinytrail.admin.dao.entity.GroupDO;
import org.tinytrail.admin.dao.mapper.GroupMapper;
import org.tinytrail.admin.service.GroupService;
import org.tinytrail.admin.toolkit.RandomGenerator;

import java.util.Objects;

/**
 * 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Override
    public void saveGroup(String groupName) {
        // 防止gid 重复
        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        } while (!hasGid(gid));

        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                // TODO 设置用户名(网关获取)
                .eq(GroupDO::getUsername, null);
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        return hasGroupFlag == null;
    }

}
