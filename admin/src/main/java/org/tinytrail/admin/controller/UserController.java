package org.tinytrail.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tinytrail.admin.common.convention.result.Result;
import org.tinytrail.admin.common.convention.result.Results;
import org.tinytrail.admin.common.enums.UserErrorCodeEnum;
import org.tinytrail.admin.dto.req.UserRegisterReqDTO;
import org.tinytrail.admin.dto.resp.UserRespDTO;
import org.tinytrail.admin.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 查询用户信息(脱敏)
     * get请求
     */
    @GetMapping("api/tiny-trail/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        if (result == null) {
            System.out.println("null...");
            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code()).setMessage(UserErrorCodeEnum.USER_NULL.message());
        }
        return Results.success(result);
    }

    /**
     * 查询用户名是否存在
     */
    @GetMapping("api/tiny-trail/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 新增用户
     */
    @PostMapping("api/tiny-trail/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO registerParam) {
        userService.register(registerParam);
        return Results.success();
    }

}
