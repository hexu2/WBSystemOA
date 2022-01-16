import java.sql.SQLException;

import org.junit.Test;

import com.njwangbo.oa.dao.WorkFlowDao;
import com.njwangbo.oa.dao.impl.WorkFlowDaoImpl;
import com.njwangbo.oa.factory.ApplicationContext;

public class TestWorkFlowDaoImpl {

	public static void main(String[] args) throws SQLException {
		WorkFlowDao flowDao =  new WorkFlowDaoImpl();
		flowDao.queryByworkId(1);
		System.out.println(flowDao.queryByworkId(2));
	}

}
