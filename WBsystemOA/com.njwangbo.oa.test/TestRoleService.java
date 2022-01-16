import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.RoleService;
import com.njwangbo.oa.util.PageModel;

/**
 * RoleService测试类
 * @author Administrator
 *
 */
public class TestRoleService {
	/*	*/
	RoleService roleService = (RoleService)ApplicationContext.getBean("roleService");
	
	//查询所有角色============================
	@Test
	public void queryAllRoles() throws OAException{
		
		List<Role> roleList = roleService.queryAllRoles();
	
		for(Role role:roleList){
			System.out.println(role.getRoleId()+"----"+role.getRoleName()+"----"+role.getCreateTime());
		}
	}
	
	//分页查询所有角色 ,=================
	@Test
	public void queryByPage() throws SQLException, OAException{
		PageModel<Role> pageModel = new PageModel<Role>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(5);
		
		pageModel = roleService.queryByPageModel(2, 3);
		List<Role> roleList = pageModel.getDataList();
		for(Role role :roleList){
			System.out.println("roleI"+"---"+role.getRoleId()+"roleName"+"---"+role.getRoleName()+"createTime"+"---"+role.getCreateTime());
		}
	}
	
	//查询所有角色数量==================
	@Test
	public void cnt() throws OAException{
		System.out.println(roleService.queryCnt());
	}
	//根据id查角色========================
	@Test
	public void queryById() throws OAException{
		Role role = roleService.queryById(1);
		System.out.println(role.getRoleId()+"----"+role.getRoleName()+"----"+role.getCreateTime());
	}
	
	
	
	//根据id删除角色
	@Test
	public void deleteRole() throws OAException{
		roleService.deleteByRoleId(1);
	}
	
	//添加新角色 已经测试通过
	@Test
	public void addRole() throws SQLException, OAException{
		Role role = new Role();
		role.setRoleName("qqqq");
		roleService.addRole(role);
	}
	
	//修改角色根据id
	@Test
	public void updateRole() throws SQLException, OAException{
		Role role = new Role();
		role.setRoleId(6);
		role.setRoleName("xxxx");
		roleService.updateRole(role);
	}
	

}
