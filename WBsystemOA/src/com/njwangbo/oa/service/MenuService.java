package com.njwangbo.oa.service;

import java.util.List;

import com.njwangbo.oa.entity.Menu;
import com.njwangbo.oa.exception.OAException;

public interface MenuService {
	/**
	 * 查询数据库菜单
	 * 
	 * @return
	 * @throws OAException
	 */
	List<Menu> queryMenu() throws OAException;

	/**
	 * 根据角色查询菜单
	 * 
	 * @param string
	 * @return
	 * @throws OAException
	 */
	List<Menu> queryMenuByRole(String string) throws OAException;
	
	/**
	 * 查询所有菜单
	 * @return
	 * @throws OAException
	 */
	List<Menu> queryAllMenu() throws OAException;
}
