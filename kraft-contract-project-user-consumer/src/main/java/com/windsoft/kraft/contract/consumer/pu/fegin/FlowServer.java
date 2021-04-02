package com.windsoft.kraft.contract.consumer.pu.fegin;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.fegin.fallback.FlowServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Primary
@FeignClient(value = "kraft-contract-flow-server", fallback = FlowServerFallback.class)
public interface FlowServer {

    @PostMapping("flow/project/{projectId}/create")
    public JsonResult createProjectFlow(@PathVariable("projectId") Long projectId, @RequestBody String json);
}
