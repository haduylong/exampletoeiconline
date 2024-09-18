package vn.myclass.core.service;

import java.util.Map;

public interface ExaminationService {
	public Object[] findByProperty(Map<String, Object> properties, String sortExpression, String sortDirection,
			int firstItem, int maxPageItems);
}
