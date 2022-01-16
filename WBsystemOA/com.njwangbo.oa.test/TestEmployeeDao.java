import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.njwangbo.oa.dao.EmployeeDao;
import com.njwangbo.oa.dao.impl.EmployeeDaoImpl;
import com.njwangbo.oa.entity.Employee;
import com.njwangbo.oa.util.PageModel;


public class TestEmployeeDao {
	public static void main(String[] args) throws SQLException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		Employee employee = null;
//		List<Employee> employeelist = null;
//		employeelist = employeeDao.queryAll();
//		Iterator<Employee> it = employeelist.iterator();
//		while(it.hasNext()){
//			employee = it.next();
//			System.out.println(employee.getEmpDeptNo());
//		}
		//测试模糊查询分页
/*		*/
 		PageModel<Employee> pageModel = new PageModel<Employee>();
 
		
		pageModel.setPageNo(1);
		pageModel.setPageSize(3);
		
		System.out.println(pageModel.getPageNo());
		System.out.println(pageModel.getPageSize());
		
		pageModel = employeeDao.queryByPageModelForBtn("李", "总经办", pageModel);
		
		List<Employee> employeeList = pageModel.getDataList();
		
		System.out.println(employeeList.size());
		Iterator<Employee> it = employeeList.iterator();
		while(it.hasNext()){
			employee = it.next();
			System.out.println("empName---:"+employee.getEmpName());
			System.out.println("deptName---:"+employee.getEmpDeptNo());
		}
		
		
		System.out.println("模糊查询后总页数---："+employeeDao.queryCntAfterPage("李", "总经办"));
		
	}

}
