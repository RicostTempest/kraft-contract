package kraft.contract.flow;

import com.windsoft.kraft.contract.server.flow.FlowServerApplication;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowServerApplication.class)
public class TestProjectCreate {

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

    @Test
    public void testDeployment(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .name("报销流程")
                .addClasspathResource("bpmn/project-invoice.bpmn")
                .deploy();
        System.out.println("流程部署id"+deployment.getId());
        System.out.println("流程部署的名字"+deployment.getName());
    }

    @Test
    public void testStartProcess(){
        Map<String,Object> assigneeMap = new HashMap<>();
        assigneeMap.put("leader","1");
        assigneeMap.put("adviser","2");
        assigneeMap.put("college","3");
        assigneeMap.put("finance","4");
        ProcessInstance myEvection = runtimeService.startProcessInstanceByKey("projectInvoice",assigneeMap);
        System.out.println("流程定义ID"+myEvection.getProcessDefinitionId());
        System.out.println("流程实例ID"+myEvection.getId());
        System.out.println("当前活动ID"+myEvection.getActivityId());
    }

    @Test
    public void personTaskSearch(){
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("projectInvoice")
                .taskAssignee("15")
                .list();
        List<Task> tasks1 = taskService.createTaskQuery()
                .processDefinitionKey("projectInvoice")
                .taskCandidateUser("15")
                .list();
        tasks.addAll(tasks1);
        System.out.println(tasks.size());
        tasks.forEach(task->{
            System.out.println("流程实例ID:" + task.getProcessInstanceId());
            System.out.println("任务ID:" + task.getId());
            System.out.println("任务负责人ID:" + task.getAssignee());
            System.out.println("任务名称:" + task.getName());
        });
    }

    @Test
    public void completeCurrentTask(){
        Task task = taskService.createTaskQuery()
                .taskId("155003")
                .taskAssignee("2")
                .singleResult();
        System.out.println("流程实例ID:" + task.getProcessInstanceId());
        System.out.println("任务ID:" + task.getId());
        System.out.println("任务负责人ID:" + task.getAssignee());
        System.out.println("任务名称:" + task.getName());
        taskService.addComment(task.getId(), task.getProcessInstanceId(), "同意申请");
        Map<String, Object> variables = new HashMap<>();
        variables.put("isAccepted",true);
//        taskService.setVariablesLocal(task.getId(), variables);
        taskService.complete(task.getId(),variables);
        ProcessInstance runProcess = runtimeService   //与正在的任务相关的Service
                .createProcessInstanceQuery()    //创建流程实例查询对象
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();     //查询条件 -- 流程的实例id(流程的实例id在流程启动后的整个流程中是不改变的)
        List<Comment> taskComments = taskService.getTaskComments(task.getId());
        taskComments.forEach(item->{
            System.out.println(item.getFullMessage());
        });

        if (runProcess == null){
            System.out.println("任务结束");
        }
    }

    @Test
    public void claimTask(){
        //  获取processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        //要拾取的任务id
        String taskId = "155003";
        //任务候选人id
        String userId = "2";
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

    @Test
    public void deleteDeployment() {
        // 流程部署id
        String deploymentId = "140001";

        //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
//        repositoryService.deleteDeployment(deploymentId);
        //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式，如果流程
        repositoryService.deleteDeployment(deploymentId, true);
    }

    @Test
    public void searchInstance(){

        ProcessInstance rpi = processEngine.getRuntimeService()    //与正在的任务相关的Service
                .createProcessInstanceQuery()    //创建流程实例查询对象
                .processInstanceId("52501")     //查询条件 -- 流程的实例id(流程的实例id在流程启动后的整个流程中是不改变的)
                .singleResult();     //返回唯一结果集
        System.out.println(rpi);
    }

    @Test
    public void searchCommentById(){
        List<Comment> historyComments= new ArrayList<>();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .processInstanceId("65001")
                .list();
        list.forEach(item->{
            List<Comment> comments = taskService.getTaskComments(item.getTaskId());

            // 4）如果当前任务有批注信息，添加到集合中
            if(comments!=null && comments.size()>0){
                historyComments.addAll(comments);
            }
        });
        historyComments.forEach(item->{
            System.out.println(item.getFullMessage());
        });

        System.out.println("=====================>");
        List<Comment> comments = taskService.getProcessInstanceComments("65001");
        comments.forEach(item->{
            System.out.println(item.getFullMessage());
        });
    }

    @Test
    public void taskList(){
        String userId = "1";
        List<Task> taskList = new ArrayList<>();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(userId)
                .list();
        List<Task> tasks1 = taskService.createTaskQuery()
                .taskCandidateUser(userId)
                .list();
        tasks.addAll(tasks1);

//        List<Task> tasks1 = taskService.createTaskQuery()
//                .processDefinitionKey("projectCreated")
//                .taskAssignee(userId)
//                .list();
        System.out.println(tasks.size());
        tasks.forEach(task -> {
            System.out.println(task.getId()+":"+task.getAssignee());
        });
    }

    @Test
    public void deleteInstances(){
        runtimeService.deleteProcessInstance("105065","流程错误");
        runtimeService.deleteProcessInstance("105020","流程错误");
        runtimeService.deleteProcessInstance("105035","流程错误");
        runtimeService.deleteProcessInstance("105050","流程错误");
    }
}
