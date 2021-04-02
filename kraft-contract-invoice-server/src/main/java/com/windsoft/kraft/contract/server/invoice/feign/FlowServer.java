package com.windsoft.kraft.contract.server.invoice.feign;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.invoice.feign.fallback.FlowServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Primary
@FeignClient(value = "kraft-contract-flow-server", fallback = FlowServerFallback.class)
public interface FlowServer {

    @PostMapping("flow/invoice/{invoiceId}/create")
    public JsonResult createInvoiceFlow(@PathVariable("invoiceId") Long invoiceId, @RequestBody String json);
}
