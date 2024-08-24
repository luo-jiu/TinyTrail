package org.tinytrail.admin.dto.req;

import lombok.Data;

/**
 * 短链接分组创建参数
 */
@Data
public class TinyTrailGroupUpdateReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名
     */
    private String name;
}
