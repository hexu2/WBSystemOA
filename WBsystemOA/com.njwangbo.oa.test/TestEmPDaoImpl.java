import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.njwangbo.oa.dao.EmployeeDao;
import com.njwangbo.oa.dao.impl.EmployeeDaoImpl;
import com.njwangbo.oa.entity.Employee;


public class TestEmPDaoImpl {
	public static void main(String[] args) throws SQLException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		List<Employee> employeeList = null;
		Employee employee = new Employee();
		employeeList = employeeDao.queryByKeys("%总经办%", "%李%");
		System.out.println(employeeList.size() );
		Iterator<Employee> it = employeeList.iterator();
		System.out.println();
		while(it.hasNext()){
			employee = it.next();
			System.out.println(employee.getEmpName());
		}
	}

}
