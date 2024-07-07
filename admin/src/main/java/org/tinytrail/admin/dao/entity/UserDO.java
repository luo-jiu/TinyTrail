package org.tinytrail.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户持久层实体
 */
@Data
@TableName("t_user")
public class UserDO {

    /**
     * ID - 自增主键
     */
    private Long id;

    /**
     * 用户名 - 用户登录名称
     */
    private String username;

    /**
     * 密码 - 用户登录密码
     */
    private String password;

    /**
     * 真实姓名 - 用户的真实姓名
     */
    private String realName;

    /**
     * 手机号 - 用户的手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

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
