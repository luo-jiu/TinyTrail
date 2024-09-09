package org.tinytrail.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tinytrail.admin.common.convention.result.Result;
import org.tinytrail.admin.common.convention.result.Results;
import org.tinytrail.admin.dto.req.TinyTrailGroupSaveReqDTO;
import org.tinytrail.admin.dto.req.TinyTrailGroupSortReqDTO;
import org.tinytrail.admin.dto.req.TinyTrailGroupUpdateReqDTO;
import org.tinytrail.admin.dto.resp.TinyTrailGroupRespDTO;
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
    @PostMapping("/api/tiny-trail/admin/v1/group")
    public Result<Void> save(@RequestBody TinyTrailGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * 查询分组集合
     */
    @GetMapping("/api/tiny-trail/admin/v1/group")
    public Result<List<TinyTrailGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改短链接分组
     */
    @PutMapping("/api/tiny-trail/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody TinyTrailGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    /**
     * 删除短链接分组
     */
    @DeleteMapping("/api/tiny-trail/admin/v1/group")
    public Result<Void> deleteGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    /**
     * 排序短连接分组
     */
    @PostMapping("/api/tiny-trail/admin/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<TinyTrailGroupSortReqDTO> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }
}
