package vn.myclass.core.service;

public interface ListenGuideLineService {
	public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit);
}
