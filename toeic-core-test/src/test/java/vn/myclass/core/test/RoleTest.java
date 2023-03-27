package vn.myclass.core.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import vn.myclass.core.dao.RoleDao;
import vn.myclass.core.daoimpl.RoleDaoImpl;
import vn.myclass.core.persistence.entity.RoleEntity;

public class RoleTest {
//	@Test
//	public void checkFindAll() {
//		RoleDao roleDao = new RoleDaoImpl();
//		List<RoleEntity> list = roleDao.findAll();
//		System.out.println(list.size());
//	}
//	
//	@Test
//	public void checkUpdateRole() {
//		RoleDao roleDao = new RoleDaoImpl();
//		RoleEntity roleEntity = new RoleEntity();
//		roleEntity.setRoleId(2);
//		roleEntity.setName("USER");
//		System.out.println(roleDao.update(roleEntity));
//	}
//	
//	@Test
//	public void checkSave() {
//		RoleDao roleDao = new RoleDaoImpl();
//		RoleEntity roleEntity = new RoleEntity();
//		roleEntity.setRoleId(3);
//		roleEntity.setName("MANAGER");
//		roleDao.save(roleEntity);
//	}
//	
//	@Test
//	public void checkFindById() {
//		RoleDao roleDao = new RoleDaoImpl();
//		System.out.println(roleDao.findById(1));
//	}
//	
//	@Test
//	public void checkFindByProperty() {
//		RoleDao roleDao = new RoleDaoImpl();
//		String property = null;
//		Object value = null;
//		String sortExpression = null;
//		String sortDirection = null;
//		property = "name";
//		value = "admin";
//		Object[] ob = roleDao.findByProperty(property, value, sortExpression, sortDirection);
//		System.out.println(ob[0].toString());
//	}
//	
//	@Test
//	public void checkDelete() {
//		List<Integer> list = new ArrayList<>();
//		list.add(1);
//		list.add(2);
//		RoleDao roleDao = new RoleDaoImpl();
//		Integer count = roleDao.delete(list);
//		System.out.println(count);
//	}
}
