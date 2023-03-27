package vn.myclass.core.serviceimpl;

import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.UserEntity;
import vn.myclass.core.service.UserService;
import vn.myclass.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService{

	@Override
	public UserDTO isUserExist(UserDTO dto) {
		UserDao dao = new UserDaoImpl();// dao thao tác với entity
		UserEntity entity = dao.isUserExist(dto.getName(), dto.getPassWord());
		if(entity == null) {
			return null; // nếu entity null thì ko convert đc nên return luôn về null
		}
		UserDTO userDTO = UserBeanUtil.entity2Dto(entity);// core-util convert giữa entity và dto
		return userDTO;
	}

	@Override
	public UserDTO findRoleByUser(UserDTO dto) {
		UserDao dao = new UserDaoImpl();
		UserEntity entity = dao.isUserExist(dto.getName(), dto.getPassWord());
		if(entity == null) {
			return null; // nếu entity null thì ko convert đc nên return luôn về null
		}
		UserDTO userDTO = UserBeanUtil.entity2Dto(entity);
		return userDTO;
	}


}
