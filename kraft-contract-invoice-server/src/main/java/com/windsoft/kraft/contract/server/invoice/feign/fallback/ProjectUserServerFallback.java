package com.windsoft.kraft.contract.server.invoice.feign.fallback;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.invoice.feign.ProjectUserServer;
import org.springframework.stereotype.Component;

@Component
public class ProjectUserServerFallback implements ProjectUserServer {
    @Override
    public JsonResult findUserInProject(Long id) {
        return JsonResult.error("项目用户服务连接失败");
    }
}
