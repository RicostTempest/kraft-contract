package com.windsoft.kraft.contract.server.invoice.controller;

import com.alibaba.fastjson.JSON;
import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.ReimburseSheet;
import com.windsoft.kraft.contract.server.invoice.dto.ReimburseSheetDto;
import com.windsoft.kraft.contract.server.invoice.feign.FlowServer;
import com.windsoft.kraft.contract.server.invoice.feign.ProjectUserServer;
import com.windsoft.kraft.contract.server.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private FlowServer flowServer;
    @Autowired
    private ProjectUserServer projectUserServer;

    @PostMapping("start/{userId}")
    public JsonResult startInvoice(@PathVariable("userId")Long id, @RequestBody ReimburseSheetDto reimburseSheetDto){
        JsonResult result = invoiceService.initSheet(reimburseSheetDto);
        Long business  = null;
        if(result.getCode() == 0){
            business = (Long) result.getData();
            result = projectUserServer.findUserInProject(1L);
            List<Long> ids = new ArrayList<>();
            List<Map> users = (List<Map>) result.getData();
            users.forEach(item->{
                if ((int)item.get("permission") == 2){
                    ids.add(Long.valueOf(item.get("id").toString()));
                }
            });
            Map<String,Object> assigneeMap = new HashMap<>();
            assigneeMap.put("leader",id.toString());
            assigneeMap.put("adviser", CommonUtils.longArrayToString( ids.toArray(new Long[0]), ","));
            assigneeMap.put("college","17");
            assigneeMap.put("finance","19");
            flowServer.createInvoiceFlow(business, JSON.toJSONString(assigneeMap));
            return JsonResult.success();
        }
        return JsonResult.error();
    }

    @GetMapping("sheet/{sheetId}/info")
    public JsonResult getReimburseSheet(@PathVariable("sheetId")Long id){
        return invoiceService.getSheetInfo(id);
    }

    @PostMapping("end/{sheetId}")
    public JsonResult userFunding(@PathVariable("sheetId")Long id, @RequestBody ReimburseSheet sheet){
        sheet.setId(id);
        return invoiceService.updateFunding(sheet);
    }

    @DeleteMapping("remove/{invoiceId}/{sheetId}")
    public JsonResult removeInvoiceFromSheet(@PathVariable("invoiceId")Long invoiceId,
                                             @PathVariable("sheetId")Long sheetId){
        return invoiceService.removeInvoiceSheet(sheetId,invoiceId);
    }

    @PostMapping("add/invoice/{sheetId}")
    public JsonResult addInvoiceFromSheet(@PathVariable("sheetId")Long sheetId,
                                          @RequestBody ReimburseSheetDto reimburseSheetDto){
        return invoiceService.addInvoice(sheetId,reimburseSheetDto);
    }
}
