package org.tinytrail.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tinytrail.admin.common.convention.result.Result;
import org.tinytrail.admin.common.convention.result.Results;
import org.tinytrail.admin.dto.req.TinyTrailGroupSaveReqDTO;
import org.tinytrail.admin.service.GroupService;

import java.util.List;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接分组
     */
    @PostMapping("/api/tiny-trail/v1/group")
    public Result<Void> save(@RequestBody TinyTrailGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * 查询分组集合
     */
    @GetMapping("/api/tiny-trail/v1/group")
    public Result<List<TinyTrailGroupSaveReqDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }
}
