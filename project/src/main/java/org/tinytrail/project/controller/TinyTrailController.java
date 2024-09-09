package org.tinytrail.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tinytrail.project.common.convention.result.Result;
import org.tinytrail.project.common.convention.result.Results;
import org.tinytrail.project.dto.req.TinyTrailCreateReqDTO;
import org.tinytrail.project.dto.req.TinyTrailPageReqDTO;
import org.tinytrail.project.dto.resp.TinyTrailCreateRespDTO;
import org.tinytrail.project.dto.resp.TinyTrailGroupCountQueryRespDTO;
import org.tinytrail.project.dto.resp.TinyTrailPageRespDTO;
import org.tinytrail.project.service.TinyTrailService;

import java.util.List;

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

    /**
     * 查询短连接分组内数量
     */
    @GetMapping("/api/tiny-trail/v1/count")
    public Result<List<TinyTrailGroupCountQueryRespDTO>> listGroupTinyTrailCount(@RequestParam("requestParam") List<String> requestParam) {
        return Results.success(tinyTrailService.listGroupTinyTrailCount(requestParam));
    }

}
