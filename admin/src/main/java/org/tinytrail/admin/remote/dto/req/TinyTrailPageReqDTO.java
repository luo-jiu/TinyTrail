package org.tinytrail.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 短连接分页请求参数
 */
@Data
public class TinyTrailPageReqDTO extends Page {

    /**
     * 分组标识
     */
    private String gid;

}
