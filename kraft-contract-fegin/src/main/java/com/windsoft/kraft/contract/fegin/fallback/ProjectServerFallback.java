package com.windsoft.kraft.contract.fegin.fallback;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.fegin.ProjectServer;
import org.springframework.stereotype.Component;

@Component
public class ProjectServerFallback implements ProjectServer {
    @Override
    public JsonResult findProject(Long id) {
        return JsonResult.error("项目服务正在维护");
    }

    @Override
    public JsonResult infoProject(Long id) {
        return JsonResult.error(400, "用户服务正在维护");
    }
}
