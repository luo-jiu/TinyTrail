package org.tinytrail.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.tinytrail.project.dao.entity.TinyTrailDO;
import org.tinytrail.project.dto.req.TinyTrailCreateReqDTO;
import org.tinytrail.project.dto.req.TinyTrailPageReqDTO;
import org.tinytrail.project.dto.resp.TinyTrailCreateRespDTO;
import org.tinytrail.project.dto.resp.TinyTrailPageRespDTO;

/**
 * 短链接接口层
 */
public interface TinyTrailService extends IService<TinyTrailDO> {

    /**
     * 创建短链接
     * @param requestParam 创建短链接请求参数
     * @return 短链接创建信息
     */
    TinyTrailCreateRespDTO createTinyTrail(TinyTrailCreateReqDTO requestParam);

    /**
     * 分页查询短连接
     * @param requestParam 分页查询短连接请求参数
     * @return 短连接分页返回结果
     */
    IPage<TinyTrailPageRespDTO> pageTinyTrail(TinyTrailPageReqDTO requestParam);
}
