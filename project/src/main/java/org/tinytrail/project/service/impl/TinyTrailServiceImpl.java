package org.tinytrail.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.tinytrail.project.common.convention.exception.ServiceException;
import org.tinytrail.project.dao.entity.TinyTrailDO;
import org.tinytrail.project.dao.mapper.TinyTrailMapper;
import org.tinytrail.project.dto.req.TinyTrailCreateReqDTO;
import org.tinytrail.project.dto.req.TinyTrailPageReqDTO;
import org.tinytrail.project.dto.resp.TinyTrailCreateRespDTO;
import org.tinytrail.project.dto.resp.TinyTrailPageRespDTO;
import org.tinytrail.project.service.TinyTrailService;
import org.tinytrail.project.toolkit.HashUtil;

/**
 * 短链接接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TinyTrailServiceImpl extends ServiceImpl<TinyTrailMapper, TinyTrailDO> implements TinyTrailService {

    private final RBloomFilter<String> tinyUriCreateCachePenetrationBloomFilter;

    @Override
    public TinyTrailCreateRespDTO createTinyTrail(TinyTrailCreateReqDTO requestParam) {
        String tinyTrailSuffix = generateSuffix(requestParam);
        String fullShortUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(tinyTrailSuffix)
                .toString();
        TinyTrailDO tinyTrailDO = TinyTrailDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .shortUri(tinyTrailSuffix)
                .enableStatus(0)
                .fullShortUri(fullShortUrl)
                .build();
        try {
            baseMapper.insert(tinyTrailDO);
        } catch (DuplicateKeyException ex) {
            // 前面有一层Bloom过滤器拦截，不用担心这里被利用来攻击
            LambdaQueryWrapper<TinyTrailDO> queryWrapper = Wrappers.lambdaQuery(TinyTrailDO.class)
                    .eq(TinyTrailDO::getFullShortUri, fullShortUrl);
            TinyTrailDO hasTinyTrailDO = baseMapper.selectOne(queryWrapper);
            if (hasTinyTrailDO != null) {
                log.warn("短链接: {} 重复入库", fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }
        }
        tinyUriCreateCachePenetrationBloomFilter.add(tinyTrailSuffix);
        return TinyTrailCreateRespDTO.builder()
                .fullTinyUrl(tinyTrailDO.getFullShortUri())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    @Override
    public IPage<TinyTrailPageRespDTO> pageTinyTrail(TinyTrailPageReqDTO requestParam) {
        LambdaQueryWrapper<TinyTrailDO> queryWrapper = Wrappers.lambdaQuery(TinyTrailDO.class)
                .eq(TinyTrailDO::getGid, requestParam.getGid())
                .eq(TinyTrailDO::getEnableStatus, 0)
                .eq(TinyTrailDO::getDelFlag, 0)
                .orderByDesc(TinyTrailDO::getCreateTime);
        IPage<TinyTrailDO> tinyTrailDOIPage = baseMapper.selectPage(requestParam, queryWrapper);
        return tinyTrailDOIPage.convert(each -> BeanUtil.toBean(each, TinyTrailPageRespDTO.class));
    }

    private String generateSuffix(TinyTrailCreateReqDTO requestParam) {
        int customGenerateCount = 0;
        String tinyUrl;
        while (true) {
            if (customGenerateCount > 10) {
                throw new ServiceException("短链接频繁生成,请稍候再试");
            }
            String originUrl = requestParam.getOriginUrl();
            originUrl += System.currentTimeMillis();
            tinyUrl = HashUtil.hashToBase62(originUrl);
            if (!tinyUriCreateCachePenetrationBloomFilter.contains(requestParam.getDomain() + "/" + tinyUrl)) {
                break;
            }
            customGenerateCount++;
        }
        return tinyUrl;
    }
}
