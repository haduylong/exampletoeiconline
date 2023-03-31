package vn.myclass.core.utils;

import vn.myclass.core.dto.ListenGuideLineDTO;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.ListenGuideLineEntity;
import vn.myclass.core.persistence.entity.UserEntity;

public class ListenGuideLineBeanUtil {
	// entity to dto
	public static ListenGuideLineDTO entity2Dto(ListenGuideLineEntity entity) {
		ListenGuideLineDTO dto = new ListenGuideLineDTO();
		dto.setListenGuideLineId(entity.getListenGuideLineId());
		dto.setCommentList(entity.getCommentList());
		dto.setContent(entity.getContent());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setImage(entity.getImage());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setTitle(entity.getTitle());
		return dto;
	}
	// dto to entity
	public static ListenGuideLineEntity dto2Entity(ListenGuideLineDTO dto) {
		ListenGuideLineEntity entity = new ListenGuideLineEntity();
		entity.setListenGuideLineId(dto.getListenGuideLineId());
		entity.setCommentList(dto.getCommentList());
		entity.setContent(dto.getContent());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setImage(dto.getImage());
		entity.setModifiedDate(dto.getModifiedDate());
		entity.setTitle(dto.getTitle());
		return entity;
	}
}
