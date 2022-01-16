import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.HolidayService;
import com.njwangbo.oa.util.DateUtil;
import com.njwangbo.oa.util.PageModel;

public class TestHolidayService {
	HolidayService holidayService = (HolidayService) ApplicationContext
			.getBean("holidayService");

	// 查询所有请假记录==========
	@Test
	public void queryAll() throws OAException {

		List<Holiday> holidayList = holidayService.queryAll();
		for (Holiday holiday : holidayList) {
			System.out.println(holiday.getHolidayNo());
		}

	}

	// 添加请假记录======
	@Test
	public void add() throws OAException {
		Holiday holiday = new Holiday();
		holiday.setHolidayNo("QJ1008");
		holiday.setHolidayUser("xx");
		holiday.setHolidayType("2");
		holiday.setHolidayBz("xx");
		holiday.setHolidayStatus("1");
		holiday.setStartTime("2016-09-26 09:00:00");
		holiday.setEndTime("2017-01-01 09:00:00");

		holidayService.addHoliday(holiday);
	}
	
	//查询请假记录总数==========
	@Test
	public void queryCnt() throws OAException{
		
		System.out.println(holidayService.queryCnt("root"));
		
	}
	

	// 删除请假记录=======
	@Test
	public void del() throws OAException {
		holidayService.delteHoliday("QJ1008");
	}

	// 修改请假记录===========
	@Test
	public void updade() throws OAException {
		Holiday holiday = new Holiday();
		holiday.setHolidayNo("QJ1007");
		holiday.setHolidayUser("xx");
		holiday.setHolidayType("2");
		holiday.setHolidayBz("xx");
		holiday.setHolidayStatus("1");
		holiday.setStartTime("2016-01-01 09:00:00");
		holiday.setEndTime("2017-01-01 09:00:00");

		holidayService.updateHoliday(holiday);
	}
	
	//根据请假编号查询请假记录==========
	@Test
	public void select() throws OAException{
		Holiday holiday = new Holiday();
		holiday = holidayService.selectHoliday("QJ1007");
		
		System.out.println(holiday.getHolidayNo());
		System.out.println(holiday.getHolidayUser());
		System.out.println(holiday.getId());
		
	}
	
	//分页查询请请假记录======
	@Test
	public void queryByPage() throws OAException {
		// 每页显示5条
		List<Holiday> HolidayList = holidayService.queryByPage(1, 5);
		for (Holiday holiday : HolidayList) {
			System.out.println(holiday.getHolidayNo());

		}
	}
	
	//按照分页模型查询请请假记录======
	@Test
	public void queryByPageModel() throws OAException {
		// 每页显示5条
		PageModel<Holiday> pageModel = new PageModel<Holiday>();
		pageModel = holidayService.queryByModel(1, 5, "root");
		
		List<Holiday> holidayList =pageModel.getDataList();
		for (Holiday holiday : holidayList) {
			System.out.println(holiday.getHolidayNo()+"\t"+ holiday.getHolidayUser() + "\n");

		}
	}
	
	//查询id最大的请假记录
	@Test
	public void queryByMaxId() throws OAException {
		Holiday holiday = new Holiday();
		holiday = holidayService.queryHolidayMaxId();
		System.out.println(holiday);
	}
	
	//按照分页模型模糊查询请请假记录======
	@Test
	public void queryByPageKeys() throws OAException {
		// 每页显示5条
		PageModel<Holiday> pageModel = new PageModel<Holiday>();
		pageModel = holidayService.queryByPageModelForBtn("", "", "", 1, 5);
		
		List<Holiday> holidayList =pageModel.getDataList();
		for (Holiday holiday : holidayList) {
			System.out.println(holiday.getHolidayNo()+"\t"+ holiday.getHolidayUser() + "\n");

		}
	}
}
