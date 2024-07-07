package org.tinytrail.admin.dto.req;


import lombok.Data;

/**
 * 用户注册请求参数
 */
@Data
public class UserRegisterReqDTO {

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

}
