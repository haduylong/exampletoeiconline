package vn.myclass.core.test;

import org.testng.annotations.Test;

import vn.myclass.core.dao.ListenGuideLineDao;
import vn.myclass.core.daoimpl.ListenGuideLineDaoImpl;

public class ListenGuidelineTest {
	@Test
	public void checkFindByProperty() {
		ListenGuideLineDao liGuideLineDao = new ListenGuideLineDaoImpl();
		String property = null;
		Object value = null;
		String sortExpression = null;
		String sortDirection = null;
		Object[] ob = liGuideLineDao.findByProperty(property, value, sortExpression, sortDirection, 7, 3);
		System.out.println(ob[0].toString());
	}
}
