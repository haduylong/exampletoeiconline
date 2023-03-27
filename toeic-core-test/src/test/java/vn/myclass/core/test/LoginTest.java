package vn.myclass.core.test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.persistence.entity.UserEntity;

public class LoginTest {
	private final Logger log = Logger.getLogger(this.getClass());
	
//	@Test
//	public void checkIsUserExist() {
//		UserDao dao = new UserDaoImpl();
//		String name = "admin";
//		String passWord = "123456";
//		UserEntity entity = new UserEntity();
//		entity = dao.isUserExist(name, passWord);
//		if(entity != null) {
//			System.out.println(entity.getRoleEntity().getName());
//		}else {
//			System.out.println("login fail!");
//		}
//	}
//	
//	@Test
//	public void checkFindRoleByUser() {
//		UserDao dao = new UserDaoImpl();
//		String name = "admin";
//		String passWord = "123456";
//		UserEntity entity = new UserEntity();
//		entity = dao.isUserExist(name, passWord);
//		log.error(entity.getRoleEntity().getRoleId()+"-"+entity.getRoleEntity().getName());
//	}
}
