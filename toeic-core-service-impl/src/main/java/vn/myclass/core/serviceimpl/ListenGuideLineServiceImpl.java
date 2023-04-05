package vn.myclass.core.serviceimpl;

import vn.myclass.core.service.ListenGuideLineService;
import vn.myclass.core.utils.ListenGuideLineBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.myclass.core.dao.ListenGuideLineDao;
import vn.myclass.core.daoimpl.ListenGuideLineDaoImpl;
import vn.myclass.core.dto.ListenGuideLineDTO;
import vn.myclass.core.persistence.entity.ListenGuideLineEntity;

public class ListenGuideLineServiceImpl implements ListenGuideLineService{
	private ListenGuideLineDao daoEntity = new ListenGuideLineDaoImpl();

	@Override
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit) {
		Object[] ob = daoEntity.findByProperty(property, sortExpression, sortDirection, offset, limit);
		List<ListenGuideLineDTO> dtos = new ArrayList<>();
		for (ListenGuideLineEntity item : (List<ListenGuideLineEntity>) ob[1]) {
			ListenGuideLineDTO temp = ListenGuideLineBeanUtil.entity2Dto(item);
			dtos.add(temp);
		}
		ob[1] = dtos;
		return ob;
	}
	

}
