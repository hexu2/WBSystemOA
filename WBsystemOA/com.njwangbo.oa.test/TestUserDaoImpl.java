import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.dao.UserDao;
import com.njwangbo.oa.dao.impl.UserDaoImpl;
import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.util.PageModel;

/**
 * 用户操作实现类测试
 * 
 * @author soft01
 * 
 */
public class TestUserDaoImpl {
	/*	*/
	User user = new User();
	
	UserDao userDao = (UserDao) ApplicationContext.getBean("userDao");

		

	// 查询用户总数量
	@Test
	public void queryAllUser() throws SQLException {
		List<User> userList = null;
		userList = userDao.queryAll();
		System.out.println(userList.size());
		for (User user : userList) {
			System.out.println(user.getId() + "---" + user.getUserName()
					+ "---" + user.getPassWord() + "---" + user.getUserRole()
					+ "---" + user.getCreateTime());
		}
	}
	
	// 根据用户名和密码查询用户
	@Test
	public void queryByNameAndPwd() throws SQLException {
		User user = userDao.queryByNameAndPwd("root", "root");
		System.out.println(user.getId() + "---" + user.getUserName() + "---"
				+ user.getPassWord() + "---" + user.getUserRole() + "---"
				+ user.getCreateTime());
	}
	//添加用户
	   @Test
		public void add() throws SQLException{

			user.setUserName("hexu");
			user.setPassWord("hexu");
			user.setEmployeeNo("12344");
			user.setUserStatus(2);
			user.setUserRole("1");
			userDao.add(user);
		}
		//根据用户id删除用户
	   @Test
		public void deleteByUserId() throws SQLException{

			userDao.deleteById(1);
		}
	   
		//根据用户id修改用户信息
	   @Test
		public void updateUser() throws SQLException{
		   	user.setId(3);
			user.setUserName("hexu");
			user.setPassWord("hexu");
			user.setEmployeeNo("12344");
			user.setUserStatus(2);
			user.setUserRole("1");
			userDao.update(user);
		}
	   
		//查询用户总数量
	   @Test
		public void queryCnt() throws SQLException{
			System.out.println(userDao.queryCnt());
		}
	   
		//按照分页模型查询
	   @Test
		public void queryByPage() throws SQLException{
		   	PageModel<User> pageModel = new PageModel<User>();
			pageModel.setPageNo(1);
			pageModel.setPageSize(5);
			
			
			pageModel = userDao.queryByPageModel(pageModel);
			
	
			List<User> userList = pageModel.getDataList();
			
			System.out.println("总用户条数----："+pageModel.getCnt());
			
			System.out.println(userList.size());
			Iterator<User> it = userList.iterator();
			while(it.hasNext()){
				user = it.next();
				System.out.println(user.getUserName());
		   
			}
	   }
	   
		//按照分页模型模糊查询
	   @Test
		public void queryByKeys() throws SQLException{
		   	PageModel<User> pageModel = new PageModel<User>();
			pageModel.setPageNo(1);
			pageModel.setPageSize(2);
			
			String userName = "%%";
			String userStatus ="%%";
			String userRole = "%%";
			pageModel = userDao.queryByKeys(userName, userStatus, userRole, pageModel);
			List<User> userList = pageModel.getDataList();
			System.out.println(userList.size());
			System.out.println("总用户条数----："+pageModel.getCnt());
			
			System.out.println(userList.size());
			Iterator<User> it = userList.iterator();
			while(it.hasNext()){
				user = it.next();
				System.out.println(user.getUserName());
		   
			}
	   }
	   
		//模糊查询分页后的员工总数
	   @Test
		public void queryCntAfterPage() throws SQLException{
			
			String userName = "%%";
			String userStatus = "%%";
			String userRole = "%管理员%";
			int cnt = userDao.queryCntAfterPage(userName, userStatus, userRole);

			System.out.println(cnt);
	   }
/*	public static void main(String[] args) throws SQLException {
		UserDao userDao = (UserDao) ApplicationContext.getBean("userDao");
		  
	   	PageModel<User> pageModel = new PageModel<User>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(3);
		
		
		pageModel = userDao.queryByPageModel(pageModel);
		
		User user = new User();

		List<User> userList = pageModel.getDataList();
		
		System.out.println("总请假条数----："+pageModel.getCnt());
		
		System.out.println(userList.size());
		Iterator<User> it = userList.iterator();
		while(it.hasNext()){
			user = it.next();
			System.out.println(user.getUserName());
		}
	}*/
		//模糊查询用户根据用户名
		@Test
		public void queryByInputUserName() throws OAException, SQLException{
			List<User> userList = userDao.queryByInputName("x");
			System.out.println(userList.size());
		}

		//根据用户名查询用户
		@Test
		public void queryByUsesrName() throws Exception{
			User user = userDao.queryByUserName("root");
			System.out.println(user.getEmployeeNo());
			
		}
		//根据用户id查找用户
		@Test
		public void queryByUsesrId() throws Exception{
			User user = userDao.queryByUserId("2");
			System.out.println(user.getEmployeeNo());
			
		}
}
