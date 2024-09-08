package org.tinytrail.admin.remote.dto;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import org.tinytrail.admin.common.convention.result.Result;
import org.tinytrail.admin.remote.dto.req.TinyTrailCreateReqDTO;
import org.tinytrail.admin.remote.dto.req.TinyTrailPageReqDTO;
import org.tinytrail.admin.remote.dto.resp.TinyTrailCreateRespDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 短链接中台远程调用中台
 */
public interface TinyTrailRemoteService {

    default Result<TinyTrailCreateRespDTO> createTinyTrail(TinyTrailCreateReqDTO requestParam) {
        System.out.println(JSON.toJSONString(requestParam));
        String resultBodyStr = HttpUtil.post("http://127.0.0.1:8001/api/tiny-trail/v1/create", JSON.toJSONString(requestParam));

        return JSON.parseObject(resultBodyStr, new TypeReference<>(){});
    }


    default String pageTinyTrail(TinyTrailPageReqDTO requireParam) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gid", requireParam.getGid());
        requestMap.put("current", requireParam.getCurrent());
        requestMap.put("size", requireParam.getSize());
        return HttpUtil.get("http://127.0.0.1:8001/api/tiny-trail/v1/page", requestMap);
    }

}
