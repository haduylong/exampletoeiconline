package vn.myclass.core.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.StringUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.util.StringUtils;

import jakarta.persistence.Query;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.dto.CheckLogin;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.dto.UserImportDTO;
import vn.myclass.core.persistence.entity.RoleEntity;
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

	@Override
	public void validateImportUser(List<UserImportDTO> userImportDTOs) {
		List<String> names = new ArrayList<>();
		List<String> roles = new ArrayList<>();
		
		//add role
		for(UserImportDTO item : userImportDTOs) {
			if(item.isValid()) {// ko co loi thi them
				names.add(item.getUserName());
				if(!roles.contains(item.getRoleName())) {
					roles.add(item.getRoleName());
				}
			}
		}
		
		// tim ds role va user
		Map<String, UserEntity> userEntityMap = new HashMap<>(); // danh sach name bi trung
		Map<String, RoleEntity> roleEntityMap = new HashMap<>(); // danh sach role tu
		
		if(names.size() > 0) {
			List<UserEntity> userEntities = SingletonDaoUtil.getUserDaoImplInstance().findByListUserName(names);	
			for(UserEntity item : userEntities) {
				userEntityMap.put(item.getFullName().toUpperCase(), item); // ko phan biet nguoi dung nhap hoa hay thong
			}
		}
		
		if(roles.size() > 0) {
			List<RoleEntity> roleEntities = SingletonDaoUtil.getRoleDaoImplInstance().findByListRoleName(roles);
			for(RoleEntity item : roleEntities) {
				roleEntityMap.put(item.getName().toUpperCase(), item);
			}
		}
		
		// kiem tra ten bi trung va role ton tai hay ko
		for(UserImportDTO item : userImportDTOs) {
			String message = item.getError();
			if(item.isValid()) {// neu ko co loi tu truoc do thi thuc hien kiem tra
				UserEntity userEntity = userEntityMap.get(item.getUserName().toUpperCase()); 
				if(userEntity != null) { // ten ton tai trong ds bi trung
					message += "<br/>";
					message += "tên đăng nhập tồn tại";
				}
				
				RoleEntity roleEntity = roleEntityMap.get(item.getRoleName().toUpperCase());
				if(roleEntity == null) { // ten ton tai trong ds bi trung
					message += "<br/>";
					message += "vai trò không tồn tại";
				}
				
				if(org.apache.commons.lang.StringUtils.isNotBlank(message)) {
					item.setValid(false);
					item.setError(message.substring(5));
				}
				
			}
		}
	}

	

}
