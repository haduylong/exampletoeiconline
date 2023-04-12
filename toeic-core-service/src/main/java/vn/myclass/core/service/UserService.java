package vn.myclass.core.service;

import java.util.Map;

import vn.myclass.core.dto.CheckLogin;
import vn.myclass.core.dto.UserDTO;

public interface UserService {
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
	public UserDTO findById(Integer userId);
	public void saveUser(UserDTO dto);
	UserDTO updateUser(UserDTO dto);
	CheckLogin checkLogin(String name, String passWord);
}
