package vn.myclass.core.utils;
/*
 * hàm luân chuyển giữa entity và dto
 */

import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.UserEntity;

public class UserBeanUtil {
	// entity -> dto
	public static UserDTO entity2Dto(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setUserId(entity.getUserId());
		dto.setName(entity.getName());
		dto.setPassWord(entity.getPassWord());
		dto.setFullName(entity.getFullName());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setRoleDTO(RoleBeanUtil.entity2Dto(entity.getRoleEntity()));
		return dto;
	}
	
	public static UserEntity dto2Entity(UserDTO userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(userDto.getUserId());
		userEntity.setName(userDto.getName());
		userEntity.setPassWord(userDto.getPassWord());
		userEntity.setFullName(userDto.getFullName());
		userEntity.setCreatedDate(userDto.getCreatedDate());
		userEntity.setRoleEntity(RoleBeanUtil.dto2Entity(userDto.getRoleDTO()));
		return userEntity;
	}
}
