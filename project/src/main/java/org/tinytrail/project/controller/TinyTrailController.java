package org.tinytrail.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tinytrail.project.common.convention.result.Result;
import org.tinytrail.project.common.convention.result.Results;
import org.tinytrail.project.dto.req.TinyTrailCreateReqDTO;
import org.tinytrail.project.dto.req.TinyTrailPageReqDTO;
import org.tinytrail.project.dto.resp.TinyTrailCreateRespDTO;
import org.tinytrail.project.dto.resp.TinyTrailPageRespDTO;
import org.tinytrail.project.service.TinyTrailService;

/**
 * 短链接控制层
 */
@RestController
@RequiredArgsConstructor
public class TinyTrailController {

    private final TinyTrailService tinyTrailService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/tiny-trail/v1/create")
    public Result<TinyTrailCreateRespDTO> createTinyTrail(@RequestBody TinyTrailCreateReqDTO requestParam) {
        return Results.success(tinyTrailService.createTinyTrail(requestParam));
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/tiny-trail/v1/page")
    public Result<IPage<TinyTrailPageRespDTO>> pageTinyTrail(TinyTrailPageReqDTO requestParam) {
        return Results.success(tinyTrailService.pageTinyTrail(requestParam));
    }

}