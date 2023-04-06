package vn.myclass.core.service;

import java.util.Map;

import vn.myclass.core.dto.UserDTO;

public interface UserService {
	public UserDTO isUserExist(UserDTO dto);
	public UserDTO findRoleByUser(UserDTO dto);
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
	public UserDTO findById(Integer userId);
}
