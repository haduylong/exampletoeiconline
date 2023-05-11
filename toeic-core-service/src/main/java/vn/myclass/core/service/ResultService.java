package vn.myclass.core.service;

import java.util.List;

import vn.myclass.core.dto.ExaminationQuestionDTO;
import vn.myclass.core.dto.ResultDTO;

public interface ResultService {
	ResultDTO saveResult(String userName, Integer examinationId, List<ExaminationQuestionDTO> examinationQuestionDTOs);
}
