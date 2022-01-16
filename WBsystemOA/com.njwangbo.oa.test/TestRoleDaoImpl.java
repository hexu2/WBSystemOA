import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.dao.RoleDao;
import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.util.PageModel;

/**
 * RoleDao测试类
 * @author soft01
 *
 */
public class TestRoleDaoImpl {
	RoleDao roleDao = (RoleDao) ApplicationContext.getBean("roleDao"); 
	//根据id删除，已经测试通过
	@Test
	public void deleteRoleById() throws SQLException{
		roleDao.deleteByRoleId(2);
		
	}
	
	//根据id查询角色,已经测试通过
	@Test
	public void queryById() throws SQLException{
		Role role = roleDao.queryById(3);
		System.out.println("roleI"+"---"+role.getRoleId());
		System.out.println("roleName"+"---"+role.getRoleName());
		System.out.println("createTime"+"---"+role.getCreateTime());
		
	}
	
	//根据角色名查询角色,已经测试通过
	@Test
	public void queryByRoleName() throws SQLException{
		Role role = roleDao.queryByRoleName("管理员");
		System.out.println("roleI"+"---"+role.getRoleId());
		System.out.println("roleName"+"---"+role.getRoleName());
		System.out.println("createTime"+"---"+role.getCreateTime());
		
	}
	//分页查询所有角色 ,已经测试通过
	@Test
	public void queryByPage() throws SQLException{
		PageModel<Role> pageModel = new PageModel<Role>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(5);
		
		pageModel = roleDao.queryByPageModel(pageModel);
		List<Role> roleList = pageModel.getDataList();
		for(Role role :roleList){
			System.out.println("roleI"+"---"+role.getRoleId()+"roleName"+"---"+role.getRoleName()+"createTime"+"---"+role.getCreateTime());
		}
	}
	
	//查询所有角色数量 ,已经测试通过
	@Test
	public void cnt() throws SQLException{
		System.out.println(roleDao.queryCnt());
	}
	
	//添加新角色 已经测试通过
	@Test
	public void addRole() throws SQLException{
		Role role = new Role();
		role.setRoleName("xxxx");
		roleDao.addRole(role);
	}
	
	//修改角色根据id
	@Test
	public void updateRole() throws SQLException{
		Role role = new Role();
		role.setRoleId(5);
		role.setRoleName("xxxx");
		roleDao.updateRole(role);
	}
	//根据角色id查询关联的账户数量
	@Test
	public void queryGuanLianUser() throws SQLException{
		System.out.println(roleDao.queryGuanLianUser(2));
	}

}
