package kraft.contract.flow;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.flow.FlowServerApplication;
import com.windsoft.kraft.contract.server.flow.fegin.UserServer;
import com.windsoft.kraft.contract.server.flow.service.FlowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowServerApplication.class)
public class FlowFeignTest {
    @Autowired
    private UserServer userServer;
    @Autowired
    private FlowService flowService;

    @Test
    public void testUserServer(){

        JsonResult result = userServer.accountDetail(2L);
        Map account = (Map) result.getData();
        System.out.println(result.getData());

    }

    @Test
    public void testGetList(){
        JsonResult result = flowService.getTaskList("16");
        System.out.println(result.getData());
    }
}
