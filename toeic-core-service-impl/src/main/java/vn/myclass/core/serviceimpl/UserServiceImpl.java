package vn.myclass.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.UserEntity;
import vn.myclass.core.service.UserService;
import vn.myclass.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService{
	UserDao dao = new UserDaoImpl();// dao thao tác với entity
	@Override
	public UserDTO isUserExist(UserDTO dto) {		
		UserEntity entity = dao.findUserByUsernameAndPassword(dto.getName(), dto.getPassWord());
		if(entity == null) {
			return null; // nếu entity null thì ko convert đc nên return luôn về null
		}
		UserDTO userDTO = UserBeanUtil.entity2Dto(entity);// core-util convert giữa entity và dto
		return userDTO;
	}

	@Override
	public UserDTO findRoleByUser(UserDTO dto) {
		UserEntity entity = dao.findUserByUsernameAndPassword(dto.getName(), dto.getPassWord());
		if(entity == null) {
			return null; // nếu entity null thì ko convert đc nên return luôn về null
		}
		UserDTO userDTO = UserBeanUtil.entity2Dto(entity);
		return userDTO;
	}

	@Override
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit) {
		Object[] ob = dao.findByProperty(property, sortExpression, sortDirection, offset, limit);
		
		List<UserDTO> list = new ArrayList<>();
		for (UserEntity userEntity : (List<UserEntity>) ob[1]) {
			UserDTO temp = UserBeanUtil.entity2Dto(userEntity);
			list.add(temp);
		}
		
		ob[1] = list;
		return ob;
	}


}
