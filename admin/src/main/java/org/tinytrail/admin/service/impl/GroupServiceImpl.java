package org.tinytrail.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;
import org.tinytrail.admin.dao.entity.GroupDO;
import org.tinytrail.admin.dao.mapper.GroupMapper;
import org.tinytrail.admin.service.GroupService;

/**
 * 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
}
