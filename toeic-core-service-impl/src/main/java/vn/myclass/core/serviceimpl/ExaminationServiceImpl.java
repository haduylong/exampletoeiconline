package vn.myclass.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.myclass.core.dto.ExaminationDTO;
import vn.myclass.core.persistence.entity.ExaminationEntity;
import vn.myclass.core.service.ExaminationService;
import vn.myclass.core.serviceutils.SingletonDaoUtil;
import vn.myclass.core.utils.ExaminationBeanUtil;

public class ExaminationServiceImpl implements ExaminationService{
	@Override
	public Object[] findByProperty(Map<String, Object> properties, String sortExpression, String sortDirection,
			int firstItem, int maxPageItems) {
		Object[] objects = SingletonDaoUtil.getExaminationDaoImplInstance().findByProperty(properties, sortExpression, sortDirection, firstItem, maxPageItems, null);
		List<ExaminationDTO> dtos = new ArrayList<>();
		for(ExaminationEntity item : (List<ExaminationEntity>) objects[1]) {
			dtos.add(ExaminationBeanUtil.entity2Dto(item));
		}
		objects[1] = dtos; 
		return objects;
	}

}
