package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.njwangbo.oa.dao.MenuDao;
import com.njwangbo.oa.entity.Menu;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.service.MenuService;
import com.njwangbo.oa.transaction.Transaction;


public class MenuServiceImpl implements MenuService {
	
	private MenuDao menuDao;
	
	private Transaction tx;
	
	
	public void setTx(Transaction tx) {
		this.tx = tx;
	}


	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}


	@Override
	public List<Menu> queryMenu() throws OAException {
		
		List<Menu> menuList = new ArrayList<Menu>();
		//1.查询出一级菜单
		try {
			menuList = menuDao.queryLevelOneMenu();
			for (Menu menu : menuList) {
				//2.填充子菜单
				List<Menu> menuSonList = new ArrayList<Menu>();
				menuSonList = menuDao.querySonMenuByParentId(menu.getId());
				menu.setSonMenuList(menuSonList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return menuList;
	}
	
	//思考：假设菜单层级不固定，如何处理？--递归
	@Override
	public List<Menu> queryMenuByRole(String roleID) throws OAException {
		
		List<Menu> menuList = new ArrayList<Menu>();
		//1.查询出一级菜单
		try {
			menuList = menuDao.queryLevelOneMenuByRole(roleID);
			for (Menu menu : menuList) {
				//2.填充子菜单
				List<Menu> menuSonList = new ArrayList<Menu>();
				menuSonList = menuDao.querySonMenuByPidRole(menu.getId(),roleID);
				menu.setSonMenuList(menuSonList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return menuList;
	}


	@Override
	public List<Menu> queryAllMenu() throws OAException {
		List<Menu> menuList = new ArrayList<Menu>();
		try {
			menuList = menuDao.queryAllMenu();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return menuList;
	}
	
	

}
