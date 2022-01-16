import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.entity.Permission;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.PermissionService;
import com.njwangbo.oa.util.PageModel;

/**
 * 测试权限业务逻辑层
 * @author soft01
 *
 */
public class TestPermissionService {
	PermissionService permissionService = (PermissionService) ApplicationContext.getBean("permissionService");
	
	//按照分页模型查询权限
	@Test
	public void queryPageMode() throws OAException{
		PageModel<Permission> pageModel = new PageModel<Permission>();
		
		pageModel = permissionService.queryByPageModel(1, 5);
		
		
		List<Permission> permissionList = pageModel.getDataList();
		for(Permission permission :permissionList){
			System.out.println("MenuId---"+permission.getMenuId()+"MenuName---"+permission.getMenuName()
					+"RoleId---"+permission.getRoleId()+"RoleName---"+permission.getRoleName());
		}
	}
	
	//按照分页模型查询权限
	@Test
	public void queryByKeys() throws OAException{
		PageModel<Permission> pageModel = new PageModel<Permission>();
		
		pageModel = permissionService.queryByKeys("2", "1", 1, 5);
		
		
		List<Permission> permissionList = pageModel.getDataList();
		for(Permission permission :permissionList){
			System.out.println("MenuId---"+permission.getMenuId()+"MenuName---"+permission.getMenuName()
					+"RoleId---"+permission.getRoleId()+"RoleName---"+permission.getRoleName());
		}
	}
	//添加权限
	@Test
	public void addPermission() throws OAException{
		permissionService.addPermission(2222, 22222);
	}

}
