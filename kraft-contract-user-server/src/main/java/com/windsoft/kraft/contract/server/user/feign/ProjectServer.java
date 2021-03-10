package com.windsoft.kraft.contract.server.user.feign;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.user.feign.fallback.ProjectServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(value = "kraft-contract-project-server", fallback = ProjectServerFallback.class)
public interface ProjectServer {
    @GetMapping("/project/find/{userId}")
    public JsonResult findProject(@PathVariable("userId")Long id);
}
