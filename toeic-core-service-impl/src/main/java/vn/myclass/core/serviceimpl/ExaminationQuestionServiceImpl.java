package vn.myclass.core.serviceimpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.myclass.core.dto.ExaminationQuestionDTO;
import vn.myclass.core.persistence.entity.ExaminationQuestionEntity;
import vn.myclass.core.service.ExaminationQuestionService;
import vn.myclass.core.serviceutils.SingletonDaoUtil;
import vn.myclass.core.utils.ExaminationBeanUtil;
import vn.myclass.core.utils.ExaminationQuestionBeanUtil;

public class ExaminationQuestionServiceImpl implements ExaminationQuestionService{

	@Override
	public Object[] findExaminationQuestionByProperties(Map<String, Object> properties, String sortExpression,
			String sortDirection,  Integer firstItem,  Integer maxPageItems, Integer examinationId) {
		List<ExaminationQuestionDTO> results = new ArrayList<>();
		String whereClause = null;
		if(examinationId != null) {
			whereClause = " AND examination.examinationId = " + examinationId;
		}
		
		Object[] objects = SingletonDaoUtil.getExaminationQuestionDaoImplInstance().findByProperty(properties, 
					sortExpression, sortDirection, firstItem, maxPageItems, whereClause);
		int count = 1;
		for(ExaminationQuestionEntity item : (List<ExaminationQuestionEntity>) objects[1]) {
			ExaminationQuestionDTO dto = ExaminationQuestionBeanUtil.entity2Dto(item);
			if(dto.getParagraph() == null) {// ko danh so cho doan van, chi danh so cho cau hoi
				dto.setNumber(count);
				count++;
			}
			dto.setExamination(ExaminationBeanUtil.entity2Dto(item.getExamination()));
			results.add(dto);
		}
		
		objects[1] = results;
		return objects;
	}

}
