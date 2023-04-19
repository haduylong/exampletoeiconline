package vn.myclass.core.dao;

import java.util.List;

import vn.myclass.core.data.dao.GenericDao;
import vn.myclass.core.persistence.entity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity>{
	Object[] checkLogin(String name, String passWord);
	List<UserEntity> findByListUserName(List<String> names);
}
