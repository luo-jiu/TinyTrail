package org.tinytrail.project.dto.resp;

import lombok.Data;
import org.tinytrail.project.dao.entity.TinyTrailDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Date;

/**
 * 短连接分页返回参数
 */
@Data
public class TinyTrailPageRespDTO extends Page<TinyTrailDO>{

    /**
     * id
     */
    private Long id;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 域名
     */
    private String domain;

    /**
     * 短链接
     */
    private String shortUri;

    /**
     * 完整短链接
     */
    private String fullShortUri;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 点击量
     */
    private Integer clickNum;

    /**
     * 网页图标
     */
    private String favicon;

    /**
     * 有效期类型 0:永久有效 1:自定义
     */
    private int validDateType;

    /**
     * 有效期
     */
    private Date validDate;

}
