import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.dao.PermissionDao;
import com.njwangbo.oa.entity.Permission;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.util.PageModel;

/**
 * RoleDao测试类
 * @author soft01
 *
 */
public class TestPermissionDaoImpl {
	PermissionDao permissionDao = (PermissionDao) ApplicationContext.getBean("permissionDao");
	//分页查询所有角色 ,已经测试通过
	@Test
	public void queryByPage() throws SQLException{
		PageModel<Permission> pageModel = new PageModel<Permission>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(5);
		
		pageModel = permissionDao.queryByPageModel(pageModel);
		List<Permission> permissionList = pageModel.getDataList();
		for(Permission permission :permissionList){
			System.out.println("id++++++++++++++"+permission.getId()+"MenuId---"+permission.getMenuId()+"MenuName---"+permission.getMenuName()
					+"RoleId---"+permission.getRoleId()+"RoleName---"+permission.getRoleName());
		}
	}
	
	//角色总数量
	@Test
	public void queryCnt() throws SQLException{
		System.out.println(permissionDao.queryCnt());
	}
	
	//模糊查询后的权限总数
	@Test
	public void queryByKeys() throws SQLException{
		System.out.println(permissionDao.queryCntAfterPage("2",""));
		
	}
	//添加权限
	@Test
	public void add() throws SQLException{
		permissionDao.addPermission(11111, 11111);
	}

}
