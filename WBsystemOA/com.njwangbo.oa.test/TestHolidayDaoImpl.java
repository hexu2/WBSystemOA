import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.njwangbo.oa.dao.HolidayDao;
import com.njwangbo.oa.dao.impl.HolidayDaoImpl;
import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.util.DateUtil;
import com.njwangbo.oa.util.PageModel;
/**
 * 请假数据操作实现类测试
 * @author Administrator
 *
 */
public class TestHolidayDaoImpl {
	
	public static void main(String[] args) throws SQLException {
		HolidayDao holidayDao = new HolidayDaoImpl();
		
		//1添加请假记录
/*		Holiday holiday = new Holiday();//创建一个请假对象
		
		holiday.setHolidayNo("QJ0008");
		holiday.setHolidayUser("qq");
		holiday.setHolidayType("1");
		holiday.setHolidayBz("qq");
		holiday.setHolidayStatus("2");
		holiday.setStartTime(DateUtil.str2Date("2016-10-10", "yyyy-MM-dd"));
		holiday.setEndTime(DateUtil.str2Date("2016-10-10", "yyyy-MM-dd"));
		
		holidayDao.add(holiday);
		*/
		
		//2根据请假编号删除请假记录
		/*holidayDao.deleteById("QJ0008");*/
		
		//3查询所有请假记录----------------------------------------------->已测试通过
/*		
 		List<Holiday> holidayList = null;
		Holiday holiday = new Holiday();//创建一个请假对象
		holidayList = holidayDao.queryAll();
		Iterator<Holiday> it = holidayList.iterator();
		while(it.hasNext()){
			holiday = it.next();
			System.out.println(holiday.getHolidayNo());
		}
		*/
		
		//4根据请假编号查询请假记录----------------------------------------------->已测试通过
		/*	
		Holiday holiday = new Holiday();//创建一个请假对象
		holiday = holidayDao.queryById("QJ1009");
		System.out.println(holiday.getHolidayUser()+holiday.getHolidayType());
		*/
		
		//5请假分页查询----------------------------------------------->已测试通过
/*		
 		List<Holiday> holidayList = null;
		Holiday holiday = new Holiday();//创建一个请假对象
		holidayList = holidayDao.queryByPage(2, 3);
		Iterator<Holiday> it = holidayList.iterator();
		while(it.hasNext()){
			holiday = it.next();
			System.out.println(holiday.getHolidayNo());
		}
		*/
		//6查询请假记录总数
/*		
 		int totalHolidayNos = holidayDao.queryCnt();
		System.out.println(totalHolidayNos);
		*/
		
		//7根据请假编号修改一个请假记录
/*		
 		Holiday holiday = new Holiday();//创建一个请假对象
		
		holiday.setHolidayNo("QJ1009");
		holiday.setHolidayUser("qq");
		holiday.setHolidayType("1");
		holiday.setHolidayBz("qq");
		holiday.setHolidayStatus("2");
		holiday.setStartTime(DateUtil.str2Date("2016-10-10 12:40:55", "yyyy-MM-dd HH:mm:ss"));
		holiday.setEndTime(DateUtil.str2Date("2016-11-10 12:40:55", "yyyy-MM-dd HH:mm:ss"));
		
		holidayDao.update(holiday);
		*/
		
		//7按照分页模型查询----------------------------------------------->已测试通过
/*		
		PageModel<Holiday> pageModel = new PageModel<Holiday>();
		pageModel.setPageNo(2);
		pageModel.setPageSize(5);
		
		
		pageModel = holidayDao.queryByPageModel(pageModel);
		
		Holiday holiday = new Holiday();

		List<Holiday> holidayList = pageModel.getDataList();
		
		System.out.println("总请假条数----："+pageModel.getCnt());
		
		System.out.println(holidayList.size());
		Iterator<Holiday> it = holidayList.iterator();
		while(it.hasNext()){
			holiday = it.next();
			System.out.println(holiday.getHolidayNo());
		}
		*/
		//查询id最大的请假记录
		/*
		Holiday holiday = new Holiday();//创建一个请假对象
		holiday = holidayDao.queryHolidayMaxId();
		System.out.println(holiday);
		*/
		
		//按照分页模糊查询
		PageModel<Holiday> pageModel = new PageModel<Holiday>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(5);
		
		String holidayUser = null;
		String holidayType = null;;
		String holidayStatus = null;
		
		pageModel = holidayDao.queryByKeys(holidayUser, holidayType, holidayStatus, pageModel);
		
		Holiday holiday = new Holiday();

		List<Holiday> holidayList = pageModel.getDataList();
		
		System.out.println("总请假条数----："+pageModel.getCnt());
		
		System.out.println(holidayList.size());
		Iterator<Holiday> it = holidayList.iterator();
		while(it.hasNext()){
			holiday = it.next();
			System.out.println(holiday.getHolidayNo());
		}
	}

}
