package com.windsoft.kraft.contract.server.flow.controller;

import com.alibaba.fastjson.JSONObject;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.flow.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("flow")
public class FlowController {

    @Autowired
    private FlowService flowService;

    /**
     * 项目创建
     * @param projectId
     * @param json
     * {
     *     "leader": "1",
     *     "adviser": "2",
     *     "college": "3",
     *     "school": "4"
     * }
     * @return
     */
    @PostMapping("project/{projectId}/create")
    public JsonResult createProjectFlow(@PathVariable("projectId") Long projectId, @RequestBody String json){
        Map<String, Object> assigneeMap = (Map) JSONObject.parse(json);
        return flowService.startProcess(assigneeMap, projectId.toString(),"projectCreated");
    }

    /**
     * 项目创建
     * @param invoiceId
     * @param json
     * {
     *     "leader": "1",
     *     "adviser": "2",
     *     "college": "3",
     *     "finance": "4"
     * }
     * @return
     */
    @PostMapping("invoice/{invoiceId}/create")
    public JsonResult createInvoiceFlow(@PathVariable("invoiceId") Long invoiceId, @RequestBody String json){
        Map<String, Object> assigneeMap = (Map) JSONObject.parse(json);
        return flowService.startProcess(assigneeMap, invoiceId.toString(),"projectInvoice");
    }

    @GetMapping("create/{userId}/list")
    public JsonResult findCreateTask(@PathVariable("userId") Long userId){
        return flowService.getTaskList(userId.toString(),"projectCreated");
    }

    @GetMapping("invoice/{userId}/list")
    public JsonResult findInvoiceTask(@PathVariable("userId") Long userId){
        return flowService.getTaskList(userId.toString(),"projectInvoice");
    }

    /**
     *
     * @param taskId
     * @param json
     * {
     *     "userId" : "1",
     *     "comment" : "同意"
     *     "accept" : "false"
     * }
     * @return
     */
    @PostMapping("{taskId}/complete")
    public JsonResult completeTask(@PathVariable("taskId") Long taskId, @RequestBody String json){
        Map<String, String> commentMap = (Map) JSONObject.parse(json);
        return flowService.completeTask(taskId.toString(),commentMap.get("userId").toString(),
                commentMap.get("comment"),
                Boolean.parseBoolean(commentMap.get("accept")));
    }

    @GetMapping("{taskId}/business")
    public JsonResult getBusinessKey(@PathVariable("taskId") String taskId){
        return flowService.getBusinessKey(taskId);
    }

    @GetMapping("{processId}/comment")
    public JsonResult getComment(@PathVariable("processId") String processId){
        return flowService.getCommentList(processId);
    }
}
