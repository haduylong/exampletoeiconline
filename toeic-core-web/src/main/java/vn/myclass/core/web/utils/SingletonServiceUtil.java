package vn.myclass.core.web.utils;

import vn.myclass.core.serviceimpl.ListenGuideLineServiceImpl;
import vn.myclass.core.serviceimpl.RoleServiceImpl;
import vn.myclass.core.serviceimpl.UserServiceImpl;

public class SingletonServiceUtil {
	private static UserServiceImpl userServiceImpl = null;
	private static RoleServiceImpl roleServiceImpl = null;
	private static ListenGuideLineServiceImpl listenGuideLineServiceImpl = null;
	
	public static UserServiceImpl getUserServiceImplInstance() {
		if(userServiceImpl == null) {
			userServiceImpl = new UserServiceImpl();
		}
		return userServiceImpl;
	}
	
	public static RoleServiceImpl getRoleServiceImplInstance() {
		if(roleServiceImpl == null) {
			roleServiceImpl = new RoleServiceImpl();
		}
		return roleServiceImpl;
	}
	
	public static ListenGuideLineServiceImpl getListenGuideLineServiceImplInstance() {
		if(listenGuideLineServiceImpl == null) {
			listenGuideLineServiceImpl = new ListenGuideLineServiceImpl();
		}
		return listenGuideLineServiceImpl;
	}
}
