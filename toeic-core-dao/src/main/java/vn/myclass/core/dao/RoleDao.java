package vn.myclass.core.dao;

import java.util.List;

import vn.myclass.core.data.dao.GenericDao;
import vn.myclass.core.persistence.entity.RoleEntity;
import vn.myclass.core.persistence.entity.UserEntity;

public interface RoleDao extends GenericDao<Integer, RoleEntity>{
	List<RoleEntity> findByListRoleName(List<String> roles);
}
