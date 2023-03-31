package vn.myclass.core.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import vn.myclass.core.dao.UserDao;
import vn.myclass.core.data.daoimpl.AbtractDao;
import vn.myclass.core.persistence.entity.UserEntity;

public class UserDaoImpl extends AbtractDao<Integer, UserEntity> implements UserDao{
	@Override
	public UserEntity findUserByUsernameAndPassword(String name, String passWord) {
		UserEntity entity = new UserEntity();
		Session session = this.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			StringBuilder hql = new StringBuilder("FROM UserEntity WHERE name =:name AND passWord =: passWord");
			Query<UserEntity> query = session.createQuery(hql.toString());
			query.setParameter("name", name);
			query.setParameter("passWord", passWord);
			entity = query.uniqueResult();
			transaction.commit();
		}catch (HibernateException e) {
			transaction.rollback();
			throw e;
		}finally {
			session.close();
		}
		return entity;
	}
	
}
