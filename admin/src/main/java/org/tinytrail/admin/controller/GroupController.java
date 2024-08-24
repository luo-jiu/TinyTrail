package org.tinytrail.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tinytrail.admin.common.convention.result.Result;
import org.tinytrail.admin.common.convention.result.Results;
import org.tinytrail.admin.dto.req.TinyTrailGroupSaveReqDTO;
import org.tinytrail.admin.service.GroupService;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/api/tiny-trail/v1/group")
    public Result<Void> save(@RequestBody TinyTrailGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }
}
