import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.dao.MenuDao;
import com.njwangbo.oa.entity.Menu;
import com.njwangbo.oa.factory.ApplicationContext;

public class TestMenuDaoImpl {
	MenuDao menuDao = (MenuDao) ApplicationContext.getBean("menuDao");

	// 察看一级菜单
	@Test
	public void queryLevelOneMenu() throws SQLException {
		List<Menu> MenuList = menuDao.queryLevelOneMenu();
		for (Menu Menu : MenuList) {
			System.out.println(Menu.getMenuName());
		}
	}
	
	// 根据父亲id和角色id查询二级菜单
	@Test
	public void querySonMenuByPidRole() throws SQLException{
		List<Menu> menuList = menuDao.querySonMenuByPidRole(1, "1");
		for (Menu menu : menuList) {
			System.out.println(menu.getMenuName()+"---"+menu.getHrefUrl());
		}
	}
	
	//查询所有菜单
	@Test
	public void queryAllMenu() throws SQLException{
		List<Menu> menuList = menuDao.queryAllMenu();
		for (Menu menu : menuList) {
			System.out.println(menu.getMenuName()+"---"+menu.getHrefUrl());
		}
		
	}

}
