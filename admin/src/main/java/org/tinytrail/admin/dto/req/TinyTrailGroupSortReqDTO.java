package org.tinytrail.admin.dto.req;

import lombok.Data;

/**
 * 短链接分组排序参数
 */
@Data
public class TinyTrailGroupSortReqDTO {

    /**
     * 分组名
     */
    private String gid;

    /**
     * 排序号
     */
    private Integer sortOrder;
}
