package vn.myclass.core.data.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import vn.myclass.core.common.constant.CoreConstant;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.data.dao.GenericDao;

public class AbtractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
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
			throw e;
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit) {
		List<T> list = new ArrayList<>();
		Integer totalItem = 0;
		Session session = this.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			StringBuilder sql = new StringBuilder(" from ");
			sql.append(this.getPersistenceClassName());
			if(property != null && value != null) {
				sql.append(" where ").append(property).append("= :value ");
			}
			if(sortDirection != null && sortExpression != null) {
				sql.append(" order by ").append(sortExpression).append(sortDirection.equals(CoreConstant.SORT_ASC)?" asc ":" desc ");
			}
			Query<T> query = session.createQuery(sql.toString());
			if(value != null) {
				query.setParameter("value", value);
			}
			if(offset!=null && offset>= 0) {
				query.setFirstResult(offset);
			}
			if(limit!=null && limit>0) {
				query.setMaxResults(limit);
			}
			list = query.list();
			// lấy ra size tối đa
			StringBuilder sql2 = new StringBuilder("SELECT count(*)  from ");
			sql2.append(this.getPersistenceClassName());
			Query<T> query2 = session.createQuery(sql.toString());
			totalItem = query2.list().size();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}finally {
			session.close();
		}
		
		return new Object[] {totalItem, list};
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
			throw e;
		}finally {
			session.close();
		}
		return count;
	}
	
}
