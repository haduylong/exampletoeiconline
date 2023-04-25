package vn.myclass.core.service;

import java.util.List;
import java.util.Map;

import vn.myclass.core.dto.ListenGuideLineDTO;

public interface ListenGuideLineService {
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
	public ListenGuideLineDTO findByListenGuideLineId(String property, Integer id);
	void saveListenGuideLine(ListenGuideLineDTO listenGuideLineDTO);
	ListenGuideLineDTO updateLiGuideLine(ListenGuideLineDTO listenGuideLineDTO);
	Integer delete(List<Integer> ids);
}
