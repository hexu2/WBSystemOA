import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.entity.Status;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.StatusService;

/**
 * 提交状态测试类
 * @author Administrator
 *
 */
public class TestStatusService {
	@Test
	public void queryAllStatus() throws OAException{
		StatusService statusService = (StatusService)ApplicationContext.getBean("statusService");
		
		List<Status> statusList = statusService.queryAllStatus();
		
		Status status = null;
		Iterator<Status> it = statusList.iterator();
		while(it.hasNext()){
			status = it.next();
			System.out.println(status.getId()+"---"+status.getValue());
		}
		
	}

}
