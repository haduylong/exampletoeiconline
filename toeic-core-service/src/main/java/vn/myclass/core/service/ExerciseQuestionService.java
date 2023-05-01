package vn.myclass.core.service;

import java.util.Map;

public interface ExerciseQuestionService {
	public Object[] findByProperty(Map<String, Object> properties, String sortExpression, String sortDirection,
			int firstItem, int maxPageItems);
}
