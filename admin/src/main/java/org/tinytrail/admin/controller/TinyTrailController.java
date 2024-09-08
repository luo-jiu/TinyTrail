package org.tinytrail.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tinytrail.admin.common.convention.result.Result;
import org.tinytrail.admin.remote.dto.TinyTrailRemoteService;
import org.tinytrail.admin.remote.dto.req.TinyTrailCreateReqDTO;
import org.tinytrail.admin.remote.dto.req.TinyTrailPageReqDTO;
import org.tinytrail.admin.remote.dto.resp.TinyTrailCreateRespDTO;

/**
 * 短链接后管控制层
 */
@RestController
public class TinyTrailController {

    private final TinyTrailRemoteService tinyTrailRemoteService = new TinyTrailRemoteService() {};

    /**
     * 创建短链接
     */
    @PostMapping("/api/tiny-trail/admin/v1/create")
    public Result<TinyTrailCreateRespDTO> createTinyTrail(@RequestBody TinyTrailCreateReqDTO requestParam) {
        return tinyTrailRemoteService.createTinyTrail(requestParam);
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/tiny-trail/admin/v1/page")
    public String pageTinyTrail(TinyTrailPageReqDTO requestParam) {
        return tinyTrailRemoteService.pageTinyTrail(requestParam);
    }

}
