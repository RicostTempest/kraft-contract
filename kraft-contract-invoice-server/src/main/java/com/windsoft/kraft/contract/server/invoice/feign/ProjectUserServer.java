package com.windsoft.kraft.contract.server.invoice.feign;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.invoice.feign.fallback.ProjectUserServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(value = "kraft-contract-project-user-consumer", fallback = ProjectUserServerFallback.class)
public interface ProjectUserServer {
    @GetMapping("pu/find/user/{projectId}")
    public JsonResult findUserInProject(@PathVariable("projectId") Long id);
}
