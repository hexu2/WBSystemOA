import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.entity.Dept;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.DeptService;

public class TestDeptService {
	@Test
	public void queryAll() throws OAException {
		DeptService deptService = (DeptService) ApplicationContext
				.getBean("deptService");
		List<Dept> deptList = deptService.queryAll();
		for (Dept dept : deptList) {
			System.out.println(dept.getDeptNo());
		}

	}

	@Test
	public void add() throws OAException {
		DeptService deptService = (DeptService) ApplicationContext
				.getBean("deptService");
		Dept dept = new Dept();
		dept.setDeptNo("A0007");
		dept.setDeptName("namexxx");
		dept.setDeptLoc("locxxx");
		dept.setDeptManager("managerxxx");
		deptService.addDept(dept);
	}

	@Test
	public void queryCnt() throws OAException {
		DeptService deptService = (DeptService) ApplicationContext
				.getBean("deptService");
		System.out.println(deptService.queryCnt());
	}
}
