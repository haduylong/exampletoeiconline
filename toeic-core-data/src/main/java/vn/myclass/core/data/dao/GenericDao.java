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
	 * để hỗ trợ phân trang: có offset xác định phần tử bắt đầu của bảng trang, limit xác định giới hạn số phần tử của bảng trang
	 * */
	public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit);
	/*
	 * truyền vào một mảng các id do người dùng chọn và xóa
	 */
	public Integer delete(List<Integer> ids);
}
