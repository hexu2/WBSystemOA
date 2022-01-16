import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.UserService;
import com.njwangbo.oa.util.PageModel;


public class TestUserService {
	UserService userService = (UserService) ApplicationContext.getBean("userService");
/*	*/
	// 查询所有用户
	@Test
	public void queryAll() throws OAException{
		
		List<User> userList = null;
		userList = userService.queryAll();
		for(User user:userList){
			System.out.println(user.getUserName());
		}
	}
	// 用户登录 
	@Test
	public void login() throws OAException{
		User user = userService.userLogin("root", "root");
		 System.out.println(user.getId()+"---"+user.getUserName()+"---"+user.getPassWord()+"---"+user.getUserRole()+"---"+user.getCreateTime());
	}
	// 根据账号查询用户
	@Test
	public void queryByUserName() throws OAException{
		User user = userService.queryByUserName("hexu");
		 System.out.println(user.getId()+"---"+user.getUserName()+"---"+user.getPassWord()+"---"+user.getUserRole()+"---"+user.getCreateTime());
	}
	
	
	//增加新用户
	@Test
	public void add() throws OAException{
		User user = new User();
		user.setUserName("xx");
		user.setPassWord("xx");
		user.setEmployeeNo("xx");
		user.setUserStatus(2);
		user.setUserRole("1");
		userService.addUser(user);
	}
	
	//删除用户
	@Test
	public void delete() throws OAException{
		userService.deleteUser(5);
	}

	//修改用户信息
	@Test
	public void updateUser() throws OAException{
		User user = new User();
		user.setId(4);
		user.setUserName("xx");
		user.setPassWord("xx");
		user.setEmployeeNo("xx");
		user.setUserStatus(2);
		user.setUserRole("1");
		userService.updateUser(user);
	}
	
	//查询用户总数
	@Test
	public void queryCnt() throws OAException{
		System.out.println(userService.queryCnt());
	}

	//分页查询用户
	@Test
	public void queryByPage() throws OAException{
		PageModel<User> pageModel = userService.queryByPage(2, 2);
		for(User user:pageModel.getDataList()){
			System.out.println(user.getUserName());
		}
	}
	//按照分页模糊查询
	@Test
	public void queryByKeys() throws OAException{
		PageModel<User> pageModel = userService.queryByKeys(null,null,null, 1,5);
		System.out.println(pageModel.getDataList().size());
		for(User user:pageModel.getDataList()){
			System.out.println(user.getUserName());
		}
	}

	//模糊查询用户根据用户名
	@Test
	public void queryByInputUserName() throws OAException{
		List<User> userList = userService.queryByInputName("x");
		for (User user : userList) {
			System.out.println(user.getUserName());
		}
	}
	
	//根据用户id查询用户
	@Test
	public void queryById() throws Exception{
		User user = userService.queryByUserId("3");
		System.out.println(user.getUserName());
		System.out.println(user.getUserRole());
		
	}
}
