package vn.myclass.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import vn.myclass.core.dao.RoleDao;
import vn.myclass.core.daoimpl.RoleDaoImpl;
import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.persistence.entity.RoleEntity;
import vn.myclass.core.service.RoleService;
import vn.myclass.core.utils.RoleBeanUtil;

public class RoleServiceImpl implements RoleService{
	RoleDao dao = new RoleDaoImpl();
	@Override
	public List<RoleDTO> findAll() {
		List<RoleEntity> entities = dao.findAll();
		List<RoleDTO> dtos = new ArrayList<>();
		for(RoleEntity item : entities) {
			dtos.add(RoleBeanUtil.entity2Dto(item));
		}
		return dtos;
	}

}
