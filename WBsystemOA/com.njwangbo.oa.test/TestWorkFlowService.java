import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.entity.WorkFlowNode;
import com.njwangbo.oa.entity.WorkNodeAction;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.WorkFlowService;

/**
 * 工作流业务逻辑层测试类
 * @author soft01
 *
 */
public class TestWorkFlowService {
	WorkFlowService workFlowService = (WorkFlowService) ApplicationContext.getBean("workFlowService");

	//添加开始节点***********已经测试
	@Test
	public void addSatrtNode() throws Exception{
		//workFlowService.addSatrtNode(1, "QJ0001");
		workFlowService.addSatrtNode(101, "QJ0001");
	}
	
	//关闭当前节点打开下一个节点***********已经测试
	@Test
	public void nextNode() throws Exception{
		//workFlowService.nextNode(tableId, workId, dealUser, dealAdvices, dealType)
		workFlowService.nextNode("QJ1001", 1, "2", "xxxxx", "pass");
		
	}
	//查看当前节点***********已经测试
	@Test
	public void queryCurrentNode() throws Exception{ 
		//workFlowService.queryCurrentNode(tableId, workId)
		WorkFlowNode  workFlowNode = workFlowService.queryCurrentNode("QJ0001", 1);
		System.out.println(workFlowNode.toString());
	}
	//查询历史记录
	@Test
	public void queryHistoryByTableID() throws Exception{ 
		List<WorkNodeAction> workNodeActionList = workFlowService.queryHistoryByTableIdAndWorkId("QJ0001", "1");
		for (WorkNodeAction workNodeAction : workNodeActionList) {
			System.out.println(workNodeAction.toString());
			
		}
	}
}
