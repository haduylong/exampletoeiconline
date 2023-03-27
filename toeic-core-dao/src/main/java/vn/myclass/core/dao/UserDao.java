package vn.myclass.core.dao;

import vn.myclass.core.data.dao.GenericDao;
import vn.myclass.core.persistence.entity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity>{
	// hàm kiểm tra user nếu có thì trả về entity
	public UserEntity isUserExist(String name, String passWord);
	// hàm trả về role của user
	public UserEntity findRoleByUser(String name, String passWord);
}
