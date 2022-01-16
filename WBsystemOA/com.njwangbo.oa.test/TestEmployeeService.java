import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.dao.EmployeeDao;
import com.njwangbo.oa.entity.Employee;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.EmployeeService;
import com.njwangbo.oa.util.DateUtil;

/**
 * 员工业务逻辑测试类
 * @author soft01
 *
 */
public class TestEmployeeService {
	EmployeeService employeeService = (EmployeeService) ApplicationContext.getBean("employeeService");
	//查询所有员工
	@Test
	public void queryAll() throws OAException{
		Employee employee = null;
		List<Employee> employeeList = null;
		employeeList = employeeService.queryAll();
		Iterator<Employee> it = employeeList.iterator();
		while(it.hasNext()){
			employee = it.next();
			System.out.println(employee.getEmpDeptNo() +"---"+employee.getEmpName() );
		}
	}
	
	//添加员工
	@Test
	public void add()throws OAException{
		Employee employee = new Employee();
		employee.setEmpNo("E0011");
		employee.setEmpName("hexu");
		employee.setEmpDeptNo("A0002");
		employee.setSex("1");
		employee.setEmail("2212925883@qq.com");
		employee.setPhone("15755843966");
		employee.setEntryTime(DateUtil.str2Date("2017-09-10", "yyyy-MM-dd"));
	
		
		employeeService.addEmp(employee);//
	}
	
	//删除员工
	@Test
	public void delEmp()throws OAException{
		employeeService.delteEmp("E0011");
	}
	
	//根据编号查询员工
	@Test
	public void queryByEMpNo()throws OAException{
		Employee employee = employeeService.queryByEmpNo("E0001");
		System.out.println(employee.getEmpNo()+"---"+employee.getEmpName());
	}
	
	//修改员工
	@Test
	public void updateEmp()throws OAException{
		Employee employee = new Employee();
		employee.setEmpNo("E0004");
		employee.setEmpName("qq");
		employee.setEmpDeptNo("A0002");
		employee.setSex("1");
		employee.setEmail("2212925883@qq.com");
		employee.setPhone("15755843966");
		employee.setEntryTime(DateUtil.str2Date("2017-09-10", "yyyy-MM-dd"));
		
		employeeService.updateEmp(employee);
	}
	
	//查询员工
	@Test
	public void selectEmp()throws OAException{
		Employee employee = null;
		employee = employeeService.selectEmp("E0001");
		System.out.println(employee.getId()+"---"+employee.getEmpNo()+"\t"+employee.getEmpName()+"\t"+employee.getEmpDeptNo());
		
	}
	
	//员工总数
	@Test
	public void queryCnt()throws OAException{
		int cnt = 0;
		cnt = employeeService.queryCnt();
		System.out.println(cnt);

	}

	//模糊查询
	@Test
	public void queryByEmpNameAndDeptName()throws OAException{
		String empName = null;
		String deptName = null;
		
		Employee employee = new Employee();
		List<Employee> employeeList = null;
		employeeList = employeeService.QueryByEmpNameAndDeptName(empName, deptName);
		Iterator<Employee> it = employeeList.iterator();

		while(it.hasNext()){
			employee = it.next();
			System.out.println(employee.getEmpName()+"\t\t"+employee.getEmpDeptNo());
		}
	}
	
	//根据用户名模糊查询
	@Test
	public void queryByEmpName()throws OAException{
		String empName = "李";
		List<Employee> employeeList = employeeService.QueryByEmpName(empName);
		System.out.println(employeeList.size());
		for (Employee employee : employeeList) {
			System.out.println(employee.getEmpName());
		}
	}
}
