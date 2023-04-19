package vn.myclass.core.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.dao.RoleDao;
import vn.myclass.core.data.daoimpl.AbtractDao;
import vn.myclass.core.persistence.entity.RoleEntity;
import vn.myclass.core.persistence.entity.UserEntity;

public class RoleDaoImpl extends AbtractDao<Integer, RoleEntity> implements RoleDao{

	@Override
	public List<RoleEntity> findByListRoleName(List<String> roles) {
		List<RoleEntity> roleEntities = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String sql = " FROM RoleEntity "; ;//" FROM RoleEntity re WHERE re.name IN (:roles) ";
			Query<RoleEntity> query = session.createQuery(sql);
			// query.setParameterList("roles", roles);
			roleEntities = query.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}finally {
			session.close();
		}
		return roleEntities;
	}

}
