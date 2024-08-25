package org.tinytrail.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tinytrail.project.dao.entity.TinyLinkDO;
import org.tinytrail.project.dao.mapper.TinyLinkMapper;
import org.tinytrail.project.service.TinyLinkService;

/**
 * 短链接接口实现层
 */
@Slf4j
@Service
public class TinyLinkServiceImpl extends ServiceImpl<TinyLinkMapper, TinyLinkDO> implements TinyLinkService {

}
