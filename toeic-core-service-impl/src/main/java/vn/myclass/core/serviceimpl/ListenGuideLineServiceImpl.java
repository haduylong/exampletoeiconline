package vn.myclass.core.serviceimpl;

import vn.myclass.core.service.ListenGuideLineService;
import vn.myclass.core.utils.ListenGuideLineBeanUtil;

import java.util.ArrayList;
import java.util.List;

import vn.myclass.core.dao.ListenGuideLineDao;
import vn.myclass.core.daoimpl.ListenGuideLineDaoImpl;
import vn.myclass.core.dto.ListenGuideLineDTO;
import vn.myclass.core.persistence.entity.ListenGuideLineEntity;

public class ListenGuideLineServiceImpl implements ListenGuideLineService{
	private ListenGuideLineDao daoEntity = new ListenGuideLineDaoImpl();
	@Override
	public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection,
			Integer offset, Integer limit) {
		List<ListenGuideLineDTO> ob1DTO = new ArrayList<>();

		Object[] ob = daoEntity.findByProperty(property, value, sortExpression, sortDirection, offset, limit);
		// vòng lặp chuyển các entity sang dto
		for(ListenGuideLineEntity item : (List<ListenGuideLineEntity>) ob[1]) {
			ListenGuideLineDTO dto = ListenGuideLineBeanUtil.entity2Dto(item);
			ob1DTO.add(dto);
		}
		//
		ob[1] = ob1DTO;
		// trả về số lượng dto và list dto
		return ob ;
	}

}
