package com.windsoft.kraft.contract.consumer.pu.fegin.fallback;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.fegin.FlowServer;
import org.springframework.stereotype.Component;

@Component
public class FlowServerFallback implements FlowServer {
    @Override
    public JsonResult createProjectFlow(Long projectId, String json) {
        return JsonResult.error(400, "流程服务连接失败");
    }
}
