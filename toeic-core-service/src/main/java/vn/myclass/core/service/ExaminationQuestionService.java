package vn.myclass.core.service;

import java.util.Map;

public interface ExaminationQuestionService {
	Object[] findExaminationQuestionByProperties(Map<String, Object> properties, String sortExpression, String sortDirection,
			Integer firstItem, Integer maxPageItems, Integer exerciseId);
}
