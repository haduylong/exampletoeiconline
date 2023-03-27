package vn.myclass.core.service;

import vn.myclass.core.dto.UserDTO;

public interface UserService {
	public UserDTO isUserExist(UserDTO dto);
	public UserDTO findRoleByUser(UserDTO dto);
}
