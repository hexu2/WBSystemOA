import java.sql.SQLException;
import java.util.List;


import org.junit.Test;

import com.njwangbo.oa.entity.Menu;
import com.njwangbo.oa.entity.MenuSon;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.MenuService;


/**
 * menuservice测试类
 * @author soft01
 *
 */
public class TestMenuService {
	private MenuService menuService = (MenuService)ApplicationContext.getBean("menuService");
	
	// 察看一级菜单
	@Test
	public void queryLevelOneMenu() throws SQLException, OAException {
		List<Menu> menuList = menuService.queryMenuByRole("1");
		for (Menu menu : menuList) {
			System.out.println(menu.getMenuName()+"---------------一级菜单");
			List<Menu> menuSonList = menu.getSonMenuList();
			for(Menu menuSon : menuSonList){
				System.out.println(menuSon.getMenuName()+"***"+menuSon.getHrefUrl()+"+++++++子菜单");
			}
		}
	}
	
	//查询所有菜单
	@Test
	public void queryAllMenu() throws OAException{
		List<Menu> menuList = menuService.queryAllMenu();
		for(Menu menu : menuList){
			System.out.println(menu.getMenuName()+"***"+menu.getHrefUrl());
		}
		
	}

}
