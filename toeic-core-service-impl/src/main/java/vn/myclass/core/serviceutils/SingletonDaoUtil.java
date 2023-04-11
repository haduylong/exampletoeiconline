package vn.myclass.core.serviceutils;

import vn.myclass.core.daoimpl.ListenGuideLineDaoImpl;
import vn.myclass.core.daoimpl.RoleDaoImpl;
import vn.myclass.core.daoimpl.UserDaoImpl;

public class SingletonDaoUtil {
	private static UserDaoImpl userDaoImpl = null;
	private static RoleDaoImpl roleDaoImpl = null;
	private static ListenGuideLineDaoImpl listenGuideLineDaoImpl = null;
	
	public static UserDaoImpl getUserDaoImplInstance() {
		if(userDaoImpl == null) {
			userDaoImpl = new UserDaoImpl();
		}
		return userDaoImpl;
	}
	
	public static RoleDaoImpl getRoleDaoImplInstance() {
		if(roleDaoImpl == null) {
			roleDaoImpl = new RoleDaoImpl();
		}
		return roleDaoImpl;
	}
	
	public static ListenGuideLineDaoImpl getListenGuideLineDaoImplInstance() {
		if(listenGuideLineDaoImpl == null) {
			listenGuideLineDaoImpl = new ListenGuideLineDaoImpl();
		}
		return listenGuideLineDaoImpl;
	}
}
