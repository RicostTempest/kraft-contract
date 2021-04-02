package kraft.contract.flow;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class TestCandidate {
    @Test
    public void testDeployment(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .name("出差申请流程-组任务")
                .addClasspathResource("bpmn/evection-candidate.bpmn")
                .deploy();
        System.out.println("流程部署id"+deployment.getId());
        System.out.println("流程部署的名字"+deployment.getName());
    }

    @Test
    public void testStartProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance myEvection = runtimeService.startProcessInstanceByKey("testCandidate");
        System.out.println("流程定义ID"+myEvection.getProcessDefinitionId());
        System.out.println("流程实例ID"+myEvection.getId());
        System.out.println("当前活动ID"+myEvection.getActivityId());
    }

    @Test
    public void completeCurrentTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("testCandidate")
                .taskAssignee("Tom")
                .singleResult();
        System.out.println("流程实例ID" + task.getProcessInstanceId());
        System.out.println("任务ID" + task.getId());
        System.out.println("任务负责人ID" + task.getAssignee());
        System.out.println("任务名称" + task.getName());
        taskService.complete(task.getId());
    }

    /**
     * 查询组任务
     */
    @Test
    public void findGroupTaskList() {
        // 流程定义key
        String processDefinitionKey = "testCandidate";
        // 任务候选人
        String candidateUser = "lisi";
        //  获取processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 创建TaskService
        TaskService taskService = processEngine.getTaskService();
        //查询组任务
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .taskCandidateUser(candidateUser)//根据候选人查询
                .list();
        for (Task task : list) {
            System.out.println("----------------------------");
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    /**
     * 查询组任务
     */
    @Test
    public void claimTask(){
        //  获取processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        //要拾取的任务id
        String taskId = "47502";
        //任务候选人id
        String userId = "lisi";
        //拾取任务
        //即使该用户不是候选人也能拾取(建议拾取时校验是否有资格)
        //校验该用户有没有拾取任务的资格
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskCandidateUser(userId)//根据候选人查询
                .singleResult();
        if(task!=null){
            //拾取任务
            taskService.claim(taskId, userId);
            System.out.println("任务拾取成功");
        }
    }

    /*
     *归还组任务，由个人任务变为组任务，还可以进行任务交接
     */
    @Test
    public void setAssigneeToGroupTask() {
        //  获取processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 查询任务使用TaskService
        TaskService taskService = processEngine.getTaskService();
        // 当前待办任务
        String taskId = "47502";
        // 任务负责人
        String userId = "lisi";
        // 校验userId是否是taskId的负责人，如果是负责人才可以归还组任务
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .taskAssignee(userId)
                .singleResult();
        if (task != null) {
            // 如果设置为null，归还组任务,该 任务没有负责人
            taskService.setAssignee(taskId, null);
        }
    }

    /*完成任务*/
    @Test
    public void completeTask(){
//     任务ID
        String taskId = "47502";
//     获取processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("完成任务："+taskId);
    }
}
