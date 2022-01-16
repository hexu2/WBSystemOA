import java.util.List;

import org.junit.Test;

import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.entity.Properties;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.PropertiesService;


public class TestPropertiesService {
 
//	//测试通过
//	
//	public static void main(String[] args) throws OAException {
//		PropertiesService propertiesService = (PropertiesService) ApplicationContext
//		.getBean("propertiesService");
//		
//		List<Properties> propertiesList = null;
//		propertiesList = propertiesService.queryAllProperties();
//		
//		for (Properties properties2 : propertiesList) {
//			System.out.println(properties2.getKeyId()+"\t"+ properties2.getPageValue() + "\n");
//
//		}
//	}
	PropertiesService propertiesService = (PropertiesService) ApplicationContext
	.getBean("propertiesService");
	
	@Test
	public void queryAllHolidayProperties() throws Exception{
		List<Properties> propertiesList = propertiesService.queryAllHolidayProperties();
		
		for (Properties properties2 : propertiesList) {
			System.out.println(properties2.getKeyId()+"\t"+ properties2.getPageValue() + "\n");

		}	
	}
	
}
