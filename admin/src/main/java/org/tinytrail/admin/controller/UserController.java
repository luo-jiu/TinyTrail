package org.tinytrail.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tinytrail.admin.common.convention.result.Result;
import org.tinytrail.admin.common.convention.result.Results;
import org.tinytrail.admin.dto.req.UserRegisterReqDTO;
import org.tinytrail.admin.dto.resp.UserActualRespDTO;
import org.tinytrail.admin.dto.resp.UserRespDTO;
import org.tinytrail.admin.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 查询用户信息(脱敏)
     */
    @GetMapping("api/tiny-trail/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 查询用户信息(不脱敏)
     */
    @GetMapping("api/tiny-trail/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        // 使用BeanUtil 包一层返回就不会脱敏了
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
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
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

}
