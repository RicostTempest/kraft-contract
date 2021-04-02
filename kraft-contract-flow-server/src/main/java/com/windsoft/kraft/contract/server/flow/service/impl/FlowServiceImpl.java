package com.windsoft.kraft.contract.server.flow.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.flow.dto.CommentDto;
import com.windsoft.kraft.contract.server.flow.dto.TaskDto;
import com.windsoft.kraft.contract.server.flow.fegin.UserServer;
import com.windsoft.kraft.contract.server.flow.service.FlowService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserServer userServer;


    @Override
    public JsonResult startProcess(Map<String, Object> assigneeMap, String business,String processName) {
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName,business,assigneeMap);
            Task task = taskService.createTaskQuery()
                    .processInstanceId(processInstance.getId())
                    .singleResult();
            completeTask(task.getId(),task.getAssignee(),"发起申请",true);
        }catch (ActivitiException e){
            System.out.println(e.getMessage());
            return JsonResult.error(e.getMessage());
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult completeTask(String taskId, String userId, String comment, Boolean accept) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(userId)
                .singleResult();
        if (task == null){
            task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskCandidateUser(userId)
                .singleResult();
            if (task != null){
                taskService.claim(taskId, userId);
            }else{
                return JsonResult.error("无权处理该任务");
            }
        }
        taskService.addComment(task.getId(), task.getProcessInstanceId(), comment);
        Map<String, Object> variables = new HashMap<>();
        variables.put("isAccepted", accept);
        taskService.complete(task.getId(),variables);
        ProcessInstance runProcess = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        if (runProcess == null){
            //任务已完成
            return JsonResult.success(true);
        }else {
            //持有后续结点
            return JsonResult.success(false);
        }

    }

    @Override
    public JsonResult getTaskList(String userId) {
        List<TaskDto> taskDtos = new ArrayList<>();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(userId)
                .list();
        List<Task> tasks1 = taskService.createTaskQuery()
                .taskCandidateUser(userId)
                .list();
        tasks.addAll(tasks1);
        tasks.forEach(task -> {
            TaskDto taskDto = new TaskDto();
            List<HistoricTaskInstance> list = historyService
                    .createHistoricTaskInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).orderByHistoricTaskInstanceEndTime()
                    .desc()
                    .list();
            HistoricTaskInstance taskInstance = null;
            if (!list.isEmpty()) {
                if (list.get(0).getEndTime() != null) {
                    taskInstance = list.get(0);
                    JsonResult result = userServer.accountDetail(Long.parseLong(taskInstance.getAssignee()));
                    if(result.getData()!=null){
                        taskDto.setAccount((Map<String, String>) result.getData());
                    }
                }
            }
            if (task.getAssignee() != null){
                taskDto.setAssigneeId(Long.parseLong(task.getAssignee()));
            }
            taskDto.setCreateTime(task.getCreateTime());
            taskDto.setName(task.getName());
            taskDto.setTaskId(task.getId());
            taskDto.setProcessInstanceId(task.getProcessInstanceId());
            taskDtos.add(taskDto);
        });
        return JsonResult.success(taskDtos);
    }

    @Override
    public JsonResult getBusinessKey(String taskId){
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        return JsonResult.success(Long.parseLong(processInstance.getBusinessKey()));
    }

    @Override
    public JsonResult getCommentList(String processId){
        List<CommentDto> historyComments= new ArrayList<>();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .processInstanceId(processId)
                .list();
        list.forEach(item->{
            List<Comment> comments = taskService.getTaskComments(item.getTaskId());
            // 4）如果当前任务有批注信息，添加到集合中
            if(comments!=null && comments.size()>0){
                CommentDto dto = new CommentDto();
                dto.setUserId(Long.parseLong(item.getAssignee()));
                dto.setMessage(comments.get(0).getFullMessage());
                dto.setName(item.getActivityName());
                dto.setCreateTime(item.getStartTime());
                historyComments.add(dto);
            }
        });
        return JsonResult.success(historyComments);
    }
}
