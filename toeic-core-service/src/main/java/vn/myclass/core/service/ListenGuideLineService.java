package vn.myclass.core.service;

import java.util.Map;

public interface ListenGuideLineService {
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
}
