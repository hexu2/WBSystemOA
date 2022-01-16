import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.entity.BaoXiao;
import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.BaoXiaoService;
import com.njwangbo.oa.service.HolidayService;
import com.njwangbo.oa.util.PageModel;

public class TestBaoXiaoService {
	BaoXiaoService baoXiaoService = (BaoXiaoService) ApplicationContext
			.getBean("baoXiaoService");

	// 查询所有报销记录==========---------------------------------------------------
	@Test
	public void queryAll() throws OAException {

		List<BaoXiao> baoXiaoList = baoXiaoService.queryAll();
		for (BaoXiao baoXiao : baoXiaoList) {
			System.out.println(baoXiao.getBaoXiaoNo());
		}

	}

	// 添加报销记录======
	@Test
	public void add() throws OAException {
		BaoXiao baoXiao = new BaoXiao();
		baoXiao.setBaoXiaoNo("BX1008");
		baoXiao.setBaoXiaoUser("xx");
		baoXiao.setBaoXiaoType("3");
		baoXiao.setBaoXiaoMoney(333);
		baoXiao.setBaoXiaoBz("xx");
		baoXiao.setBaoXiaoStatus("1");


		baoXiaoService.addBaoXiao(baoXiao);
	}
	
	//查询报销记录总数==========
	@Test
	public void queryCnt() throws OAException{
		
		System.out.println(baoXiaoService.queryCnt("root"));
		
	}
	

	// 删除报销记录=======
	@Test
	public void del() throws OAException {
		baoXiaoService.delteBaoXiao("BX1006");
	}

	// 修改报销记录===========
	@Test
	public void updade() throws OAException {
		BaoXiao baoXiao = new BaoXiao();
		baoXiao.setBaoXiaoNo("BX1005");
		baoXiao.setBaoXiaoUser("xx");
		baoXiao.setBaoXiaoType("2");
		baoXiao.setBaoXiaoBz("xx");
		baoXiao.setBaoXiaoStatus("1");


		baoXiaoService.updateBaoXiao(baoXiao);
	}
	
	//根据报销编号查询报销记录==========
	@Test
	public void select() throws OAException{
		BaoXiao baoXiao = new BaoXiao();
		baoXiao = baoXiaoService.selectBaoXiao("BX1005");
		
		System.out.println(baoXiao.getBaoXiaoNo());
		System.out.println(baoXiao.getBaoXiaoUser());
		System.out.println(baoXiao.getId());
		
	}
	
	//分页查询请报销记录======
	@Test
	public void queryByPage() throws OAException {
		// 每页显示5条
		List<BaoXiao> baoXiaoList = baoXiaoService.queryByPage(2, 2);
		for (BaoXiao baoXiao : baoXiaoList) {
			System.out.println(baoXiao.getBaoXiaoNo());

		}
	}
	
	//按照分页模型查询请报销记录======
	@Test
	public void queryByPageModel() throws OAException {
		// 每页显示5条
		PageModel<BaoXiao> pageModel = new PageModel<BaoXiao>();
		pageModel = baoXiaoService.queryByModel(2, 2, "root");
		
		List<BaoXiao> baoXiaoList =pageModel.getDataList();
		for (BaoXiao baoXiao : baoXiaoList) {
			System.out.println(baoXiao.getBaoXiaoNo()+"\t"+ baoXiao.getBaoXiaoUser() + "\n");

		}
	}
	
	//查询id最大的报销记录
	@Test
	public void queryByMaxId() throws OAException {
		BaoXiao baoXiao = new BaoXiao();
		baoXiao = baoXiaoService.queryBaoXiaoMaxId();
		System.out.println(baoXiao.getId()+"---"+baoXiao.getBaoXiaoNo());
	}
	
	//按照分页模型模糊查询请报销记录======
	@Test
	public void queryByPageKeys() throws OAException {
		// 每页显示5条
		PageModel<BaoXiao> pageModel = new PageModel<BaoXiao>();
		pageModel = baoXiaoService.queryByPageModelForBtn("root", "", "", 2, 2);
		
		List<BaoXiao> baoXiaoList =pageModel.getDataList();
		for (BaoXiao baoXiao : baoXiaoList) {
			System.out.println(baoXiao.getBaoXiaoNo()+"\t"+ baoXiao.getBaoXiaoUser() + "\n");

		}
	}
}
