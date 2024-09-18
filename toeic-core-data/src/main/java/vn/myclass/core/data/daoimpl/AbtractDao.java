package vn.myclass.core.data.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import vn.myclass.core.common.constant.CoreConstant;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.data.dao.GenericDao;

public class AbtractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
	private final Logger log = Logger.getLogger(this.getClass());
	private Class<T> persistenceClass; // kiểu class của T (tên table của T)
	
	public AbtractDao() {
		// biến <ID extends Serializable, T> thành mảng {ID, T} rồi lấy ra T
		this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	public String getPersistenceClassName() {
		// lấy ra tên của T
		return this.persistenceClass.getSimpleName();
	}
	
	protected Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	@Override
	public List<T> findAll() {
		List<T> list = new ArrayList<>();
		Session session = this.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			StringBuilder hql = new StringBuilder("from ");
			hql.append(this.getPersistenceClassName());
			
			list = session.createQuery(hql.toString()).list();
			
			transaction.commit();
		}catch (HibernateException e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			throw e;
		}finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public T update(T entity) {
		T result;
		Session session = this.getSession();
		Transaction transaction = session.beginTransaction();
		try {	
			result = session.merge(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			throw e;
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public void save(T entity) {
		Session session = this.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			throw e;
		}finally {
			session.close();
		}
	}

	@Override
	public T findById(ID id) {
		T result = null;
		Session session = this.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result = session.get(persistenceClass, id);
			if(result == null) {
				throw new ObjectNotFoundException(result , "NOT FOUND "+ id);
			}
		} catch (Exception e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			throw e;
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, String whereClause) {	
		Session session = this.getSession();
		Transaction transaction = session.beginTransaction();
		
		List<T> list = new ArrayList<>();
		Integer totalItem =  0;
		try {
			StringBuilder sql1 = new StringBuilder(" from ");
			sql1.append(this.getPersistenceClassName()).append(" where 1=1 ");
			// thêm thuộc tính truy vấn
			sql1.append(buildPropertiesQuery(property));
			
			if(whereClause != null) {
				sql1.append(whereClause);
			}
			
			if(sortDirection != null && sortExpression != null) {
				sql1.append(" order by ").append(sortExpression).append(sortDirection.equals(CoreConstant.SORT_ASC)?" asc ":" desc ");
			}
			Query query = session.createQuery(sql1.toString());
			// vị trí hàng bắt đầu và giới hạn list trả về
			if(offset!=null && offset>= 0) {
				query.setFirstResult(offset);
			}
			if(limit!=null && limit>0) {
				query.setMaxResults(limit);
			}
			list = query.list();
			
			// lấy ra size tối đa
			StringBuilder sql2 = new StringBuilder(" select count(*) from ");
			sql2.append(this.getPersistenceClassName()).append(" where 1=1 ");
			// thêm thuộc tính truy vấn
			sql2.append(buildPropertiesQuery(property));
			
			if(whereClause != null) {
				sql2.append(whereClause);
			}
			
			Query query2 = session.createQuery(sql2.toString());
			totalItem = Integer.parseInt(query2.list().get(0).toString());
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			throw e;
		}finally {
			session.close();
		}
		
		return new Object[] {totalItem, list};
	}
	
	/*
	 * 
	 */
	private String buildPropertiesQuery(Map<String, Object> properties) {
		StringBuilder sql = new StringBuilder("");
		if(properties!=null && properties.size()>0) {
			String params[] = new String[properties.size()];
			Object values[] = new Object[properties.size()];
			int i = 0;
			for(Map.Entry item : properties.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
			}
			for(int k=0; k<properties.size(); k++) {
				sql.append(" AND ").append("LOWER("+params[k]+")" + " LIKE '%"+values[k]+"%' ");
			}
			
		}
		return sql.toString();
	}

	@Override
	public Integer delete(List<Integer> ids) {
		Integer count = 0;
		Session session = this.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			for(Integer item : ids) {
				T t = (T) session.get(persistenceClass, item);
				session.delete(t);
				count++;
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			throw e;
		}finally {
			session.close();
		}
		return count;
	}

	@Override
	public T findEqualUnique(String property, Object value) {
		T result = null;
		Session session = this.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String sql = " FROM "+this.getPersistenceClassName()+" model WHERE "+property+" =:value ";
			Query<T> query = session.createQuery(sql);
			query.setParameter("value", value);
			result = query.uniqueResult();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e.getMessage(), e);
			throw e;
		}finally {
			session.close();
		}
		return result;
	}
	
}
