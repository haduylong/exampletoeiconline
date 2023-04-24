package vn.myclass.core.serviceimpl;

import vn.myclass.core.service.ListenGuideLineService;
import vn.myclass.core.serviceutils.SingletonDaoUtil;
import vn.myclass.core.utils.ListenGuideLineBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;

import vn.myclass.core.dao.ListenGuideLineDao;
import vn.myclass.core.daoimpl.ListenGuideLineDaoImpl;
import vn.myclass.core.dto.ListenGuideLineDTO;
import vn.myclass.core.persistence.entity.ListenGuideLineEntity;

public class ListenGuideLineServiceImpl implements ListenGuideLineService{
//	private ListenGuideLineDao daoEntity = new ListenGuideLineDaoImpl();

	@Override
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit) {
		Object[] ob = SingletonDaoUtil.getListenGuideLineDaoImplInstance().findByProperty(property, sortExpression, sortDirection, offset, limit);
		List<ListenGuideLineDTO> dtos = new ArrayList<>();
		for (ListenGuideLineEntity item : (List<ListenGuideLineEntity>) ob[1]) {
			ListenGuideLineDTO temp = ListenGuideLineBeanUtil.entity2Dto(item);
			dtos.add(temp);
		}
		ob[1] = dtos;
		return ob;
	}

	@Override
	public ListenGuideLineDTO findByListenGuideLineId(String property, Integer id) {
		ListenGuideLineEntity listenGuideLineEntity = SingletonDaoUtil.getListenGuideLineDaoImplInstance().findEqualUnique(property, id);
		ListenGuideLineDTO listenGuideLineDTO = ListenGuideLineBeanUtil.entity2Dto(listenGuideLineEntity);
		return listenGuideLineDTO;
	}

	@Override
	public void saveListenGuideLine(ListenGuideLineDTO listenGuideLineDTO) throws ConstraintViolationException {
		listenGuideLineDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		ListenGuideLineEntity listenGuideLineEntity = ListenGuideLineBeanUtil.dto2Entity(listenGuideLineDTO);
		SingletonDaoUtil.getListenGuideLineDaoImplInstance().save(listenGuideLineEntity);
	}

	@Override
	public ListenGuideLineDTO updateLiGuideLine(ListenGuideLineDTO listenGuideLineDTO) {
		listenGuideLineDTO.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		ListenGuideLineEntity entity = ListenGuideLineBeanUtil.dto2Entity(listenGuideLineDTO);
		entity = SingletonDaoUtil.getListenGuideLineDaoImplInstance().update(entity);
		listenGuideLineDTO = ListenGuideLineBeanUtil.entity2Dto(entity);
		return listenGuideLineDTO;
	}
	

}
