package vn.myclass.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import vn.myclass.core.dto.ExerciseDTO;
import vn.myclass.core.persistence.entity.ExerciseEntity;
import vn.myclass.core.service.ExerciseService;
import vn.myclass.core.serviceutils.SingletonDaoUtil;
import vn.myclass.core.utils.ExerciseBeanUtil;

public class ExerciseServiceImpl implements ExerciseService{

	public Object[] findByProperty(Map<String, Object> properties, String sortExpression, String sortDirection,
			int firstItem, int maxPageItems) {
		Object[] objects = SingletonDaoUtil.getExerciseDaoImplInstance().findByProperty(properties, sortExpression, sortDirection, firstItem, maxPageItems, null);
		List<ExerciseDTO> dtos = new ArrayList<>();
		for(ExerciseEntity item : (List<ExerciseEntity>) objects[1]) {
			dtos.add(ExerciseBeanUtil.entity2Dto(item));
		}
		objects[1] = dtos; 
		return objects;
	}

}
