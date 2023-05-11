package vn.myclass.core.serviceimpl;

import java.sql.Timestamp;
import java.util.List;

import vn.myclass.core.dto.ExaminationQuestionDTO;
import vn.myclass.core.dto.ResultDTO;
import vn.myclass.core.persistence.entity.ExaminationEntity;
import vn.myclass.core.persistence.entity.ResultEntity;
import vn.myclass.core.persistence.entity.UserEntity;
import vn.myclass.core.service.ResultService;
import vn.myclass.core.serviceutils.SingletonDaoUtil;
import vn.myclass.core.utils.ResultBeanUtil;
/*
  resultEntity:
  user
  examination
  listenScore
  readingScore
  createdDate
*/
public class ResultServiceImpl implements ResultService{

	@Override
	public ResultDTO saveResult(String userName, Integer examinationId,
			List<ExaminationQuestionDTO> examinationQuestionDTOs) {
		ResultDTO resultDTO = new ResultDTO();
		if(userName!=null && examinationId!=null && examinationQuestionDTOs!=null) {
			ResultEntity resultEntity = new ResultEntity();
			UserEntity user = SingletonDaoUtil.getUserDaoImplInstance().findEqualUnique("name", userName);
			ExaminationEntity examination = SingletonDaoUtil.getExaminationDaoImplInstance().findById(examinationId);
			setListenAndReadScore(resultEntity, examinationQuestionDTOs);
			setResultEntity(resultEntity, user, examination);
			SingletonDaoUtil.getResultDaoImplInstance().save(resultEntity);
			resultDTO = ResultBeanUtil.entity2Dto(resultEntity);
		}
		return resultDTO;
	}

	private void setResultEntity(ResultEntity resultEntity, UserEntity user, ExaminationEntity examination) {
		resultEntity.setExamination(examination);
		resultEntity.setUser(user);
		resultEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));	
	}

	private void setListenAndReadScore(ResultEntity resultEntity,
			List<ExaminationQuestionDTO> examinationQuestionDTOs) {
		int listenScore = 0;
		int readingScore = 0;
		for(ExaminationQuestionDTO item : examinationQuestionDTOs) {
			if(item.getAnswerUser() != null) {
				if(item.getAnswerUser().equals(item.getCorrectAnswer())) {
					if(item.getNumber() <= 4) {// listen 1-> 4
						listenScore++;
					}else {
						readingScore++;
					}
				}
			}
		}
		resultEntity.setListenScore(listenScore);
		resultEntity.setReadingScore(readingScore);
	}

	
}
