package com.windsoft.kraft.contract.server.flow.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;

import java.util.Map;

public interface FlowService {
    JsonResult startProcess(Map<String, Object> assigneeMap, String business,String processName);

    JsonResult completeTask(String taskId, String userId, String comment, Boolean accept);

    JsonResult getTaskList(String userId, String processName);

    JsonResult getBusinessKey(String taskId);

    JsonResult getCommentList(String processId);
}
