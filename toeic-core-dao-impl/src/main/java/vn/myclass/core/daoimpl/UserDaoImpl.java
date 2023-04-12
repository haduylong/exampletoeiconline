package vn.myclass.core.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.data.daoimpl.AbtractDao;
import vn.myclass.core.persistence.entity.UserEntity;

public class UserDaoImpl extends AbtractDao<Integer, UserEntity> implements UserDao{
	@Override
	public Object[] checkLogin(String name, String passWord) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Boolean isUserExist = false;
		String roleName = null;
		try {
			StringBuilder hql = new StringBuilder(" FROM UserEntity ue WHERE ue.name =:name and ue.passWord =:passWord ");
			Query<UserEntity> query = session.createQuery(hql.toString());
			query.setParameter("name", name);
			query.setParameter("passWord", passWord);
			if(query.list().size() > 0) {
				UserEntity userEntity = query.uniqueResult();
				isUserExist = true;
				roleName = userEntity.getRoleEntity().getName();
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}finally {
			session.close();
		}
		return new Object[] {isUserExist, roleName};
	}
	
}
