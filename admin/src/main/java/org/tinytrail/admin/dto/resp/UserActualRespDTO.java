package org.tinytrail.admin.dto.resp;

import lombok.Data;

/**
 * 用户返回参数响应
 */
@Data
public class UserActualRespDTO {
    /**
     * ID - 自增主键
     */
    private Long id;

    /**
     * 用户名 - 用户登录名称
     */
    private String username;

    /**
     * 真实姓名 - 用户的真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
