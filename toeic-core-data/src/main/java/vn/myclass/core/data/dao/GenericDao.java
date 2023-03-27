package vn.myclass.core.data.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<ID extends Serializable, T> {
	public List<T> findAll();
	T update(T entity);
	public void save(T entity);
	T findById(ID id);
	/*
	 * hàm trả về danh sách và số lượng 
	 * theo thuộc tính được chọn để tìm(property) 
	 * và thuộc tính đc chọn để sắp xếp(sortExpression)
	 * */
	public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection);
	public Integer delete(List<Integer> ids);
}
