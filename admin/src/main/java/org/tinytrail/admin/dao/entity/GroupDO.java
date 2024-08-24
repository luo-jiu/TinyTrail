package org.tinytrail.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 短链接分组实体
 */
@Data
@TableName("t_group")
public class GroupDO {

    /**
     * ID - 自增主键
     */
    private Long id;

    /**
     * gId - 分组标识
     */
    private Long gid;

    /**
     * 组名 - 分组名称
     */
    private String name;

    /**
     * 创建人 - 创建分组用户名
     */
    private String username;

    /**
     * 注销时间 - 用户账户的注销时间
     */
    private Long deletionTime;

    /**
     * 创建时间 - 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间 - 记录的最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除标志 - 逻辑删除标志(1:删除, 0:未删除)
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
