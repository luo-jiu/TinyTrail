package org.tinytrail.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.tinytrail.project.dao.entity.TinyTrailDO;

/**
 * 短连接分页请求参数
 */
@Data
public class TinyTrailPageReqDTO extends Page<TinyTrailDO> {

    /**
     * 分组标识
     */
    private String gid;

}
