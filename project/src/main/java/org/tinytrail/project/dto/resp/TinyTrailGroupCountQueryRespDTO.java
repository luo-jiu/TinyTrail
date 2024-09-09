package org.tinytrail.project.dto.resp;

import lombok.Data;

/**
 * 短连接分组查询返回参数
 */
@Data
public class TinyTrailGroupCountQueryRespDTO {

    /**
     * 分组表示
     */
    private String gid;

    /**
     * 短连接数量
     */
    private Integer tinyTrailCount;
}
