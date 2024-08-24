package org.tinytrail.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tinytrail.admin.database.BaseDO;


/**
 * 短链接分组实体
 */
@Data
@TableName("t_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO {

    /**
     * ID - 自增主键
     */
    private Long id;

    /**
     * gId - 分组标识
     */
    private String gid;

    /**
     * 组名 - 分组名称
     */
    private String name;

    /**
     * 创建人 - 创建分组用户名
     */
    private String username;

}
