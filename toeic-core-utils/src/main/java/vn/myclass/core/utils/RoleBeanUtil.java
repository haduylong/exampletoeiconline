package vn.myclass.core.utils;

import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.persistence.entity.RoleEntity;

public class RoleBeanUtil {
	public static RoleDTO entity2Dto(RoleEntity roleEntity) {
		RoleDTO dto = new RoleDTO();
		dto.setRoleId(roleEntity.getRoleId());
		dto.setName(roleEntity.getName());
		return dto;
	}
	
	public static RoleEntity dto2Entity(RoleDTO roleDto) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleId(roleDto.getRoleId());
		roleEntity.setName(roleDto.getName());
		return roleEntity;
	}
}
