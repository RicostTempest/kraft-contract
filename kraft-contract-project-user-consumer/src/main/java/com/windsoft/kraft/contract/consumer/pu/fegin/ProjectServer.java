package com.windsoft.kraft.contract.consumer.pu.fegin;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.fegin.fallback.ProjectServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(value = "kraft-contract-project-server", fallback = ProjectServerFallback.class)
public interface ProjectServer {
    @GetMapping("/project/find/{projectId}")
    public JsonResult findProject(@PathVariable("projectId")Long id);

    @GetMapping("/project/info/{projectId}")
    public JsonResult infoProject(@PathVariable("projectId")Long id);
}