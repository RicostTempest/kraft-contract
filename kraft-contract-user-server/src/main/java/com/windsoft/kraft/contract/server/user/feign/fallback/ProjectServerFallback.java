package com.windsoft.kraft.contract.server.user.feign.fallback;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.user.feign.ProjectServer;
import org.springframework.stereotype.Component;

@Component
public class ProjectServerFallback implements ProjectServer {
    @Override
    public JsonResult findProject(Long id) {
        return JsonResult.error("project-server服务出错");
    }
}
