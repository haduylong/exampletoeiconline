package vn.myclass.core.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.UserEntity;
import vn.myclass.core.service.UserService;
import vn.myclass.core.serviceutils.SingletonDaoUtil;
import vn.myclass.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService{
//	UserDao dao = new UserDaoImpl();// dao thao tác với entity
	@Override
	public UserDTO isUserExist(UserDTO dto) {		
		UserEntity entity = SingletonDaoUtil.getUserDaoImplInstance().findUserByUsernameAndPassword(dto.getName(), dto.getPassWord());
		if(entity == null) {
			return null; // nếu entity null thì ko convert đc nên return luôn về null
		}
		UserDTO userDTO = UserBeanUtil.entity2Dto(entity);// core-util convert giữa entity và dto
		return userDTO;
	}

	@Override
	public UserDTO findRoleByUser(UserDTO dto) {
		UserEntity entity = SingletonDaoUtil.getUserDaoImplInstance().findUserByUsernameAndPassword(dto.getName(), dto.getPassWord());
		if(entity == null) {
			return null; // nếu entity null thì ko convert đc nên return luôn về null
		}
		UserDTO userDTO = UserBeanUtil.entity2Dto(entity);
		return userDTO;
	}

	@Override
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit) {
		Object[] ob = SingletonDaoUtil.getUserDaoImplInstance().findByProperty(property, sortExpression, sortDirection, offset, limit);
		
		List<UserDTO> list = new ArrayList<>();
		for (UserEntity userEntity : (List<UserEntity>) ob[1]) {
			UserDTO temp = UserBeanUtil.entity2Dto(userEntity);
			list.add(temp);
		}
		
		ob[1] = list;
		return ob;
	}

	@Override
	public UserDTO findById(Integer userId) {
		UserEntity entity = SingletonDaoUtil.getUserDaoImplInstance().findById(userId);
		UserDTO dto = UserBeanUtil.entity2Dto(entity);
		return dto;
	}

	@Override
	public void saveUser(UserDTO dto) {
		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
		dto.setCreatedDate(createdDate);
		UserEntity entity = UserBeanUtil.dto2Entity(dto);
		SingletonDaoUtil.getUserDaoImplInstance().save(entity);
	}

	@Override
	public UserDTO updateUser(UserDTO dto) {
		UserEntity entity = UserBeanUtil.dto2Entity(dto);
		entity = SingletonDaoUtil.getUserDaoImplInstance().update(entity);
		dto = UserBeanUtil.entity2Dto(entity);
		return dto;
	}


}
