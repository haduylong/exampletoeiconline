package vn.myclass.core.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.dto.CheckLogin;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.UserEntity;
import vn.myclass.core.service.UserService;
import vn.myclass.core.serviceutils.SingletonDaoUtil;
import vn.myclass.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService{
//	UserDao dao = new UserDaoImpl();// dao thao tác với entity
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

	@Override
	public CheckLogin checkLogin(String name, String passWord) {
		CheckLogin checkLogin = new CheckLogin();
		if(name!=null && passWord!=null) {
			Object[] objects = SingletonDaoUtil.getUserDaoImplInstance().checkLogin(name, passWord);
			checkLogin.setUserExist((Boolean)objects[0]);
			if(checkLogin.isUserExist()) {
				checkLogin.setRoleName(objects[1].toString());
			}
		}
		return checkLogin;
	}

	

}
