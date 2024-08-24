package org.tinytrail.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tinytrail.admin.common.convention.result.Result;
import org.tinytrail.admin.common.convention.result.Results;
import org.tinytrail.admin.dto.req.UserLoginReqDTO;
import org.tinytrail.admin.dto.req.UserRegisterReqDTO;
import org.tinytrail.admin.dto.req.UserUpdateReqDTO;
import org.tinytrail.admin.dto.resp.UserActualRespDTO;
import org.tinytrail.admin.dto.resp.UserLoginRespDTO;
import org.tinytrail.admin.dto.resp.UserRespDTO;
import org.tinytrail.admin.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 查询用户信息(脱敏)
     */
    @GetMapping("/api/tiny-trail/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 查询用户信息(不脱敏)
     */
    @GetMapping("/api/tiny-trail/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        // 使用BeanUtil 包一层返回就不会脱敏了
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 查询用户名是否存在
     */
    @GetMapping("/api/tiny-trail/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 新增用户
     */
    @PostMapping("/api/tiny-trail/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 修改用户
     */
    @PutMapping("/api/tiny-trail/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }

    /**
     * 用户登录
     */
    @PostMapping("/api/tiny-trail/v1/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }

    /**
     * 检查用户是否登录
     */
    @GetMapping("/api/tiny-trail/v1/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }

    /**
     * 用户推出登录
     */
    @DeleteMapping("/api/tiny-trail/v1/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }
}
