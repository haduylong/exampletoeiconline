package vn.myclass.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.myclass.core.dto.ExerciseQuestionDTO;
import vn.myclass.core.persistence.entity.ExerciseEntity;
import vn.myclass.core.persistence.entity.ExerciseQuestionEntity;
import vn.myclass.core.service.ExerciseQuestionService;
import vn.myclass.core.serviceutils.SingletonDaoUtil;
import vn.myclass.core.utils.ExerciseBeanUtil;
import vn.myclass.core.utils.ExerciseQuestionBeanUtil;

public class ExerciseQuestionServiceImpl implements ExerciseQuestionService{

	@Override
	public Object[] findByProperty(Map<String, Object> properties, String sortExpression, String sortDirection,
			int firstItem, int maxPageItems) {
		Object[] objects = SingletonDaoUtil.getExerciseQuestionDaoImplInstance().findByProperty(properties, sortExpression, sortDirection, firstItem, maxPageItems);
		List<ExerciseQuestionDTO> dtos = new ArrayList<>();
		for(ExerciseQuestionEntity item : (List<ExerciseQuestionEntity>) objects[1]) {
			ExerciseQuestionDTO dto = ExerciseQuestionBeanUtil.entity2Dto(item);
			ExerciseEntity exerciseEntity = item.getExerciseEntity();
			dto.setExercise(ExerciseBeanUtil.entity2Dto(exerciseEntity));
			dtos.add(dto);
		}
		objects[1] = dtos;
		return objects;
	}

}
