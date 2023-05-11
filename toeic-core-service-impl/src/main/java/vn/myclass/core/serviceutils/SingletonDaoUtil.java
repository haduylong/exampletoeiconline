package vn.myclass.core.serviceutils;

import vn.myclass.core.daoimpl.CommentDaoImpl;
import vn.myclass.core.daoimpl.ExaminationDaoImpl;
import vn.myclass.core.daoimpl.ExaminationQuestionDaoImpl;
import vn.myclass.core.daoimpl.ExerciseDaoImpl;
import vn.myclass.core.daoimpl.ExerciseQuestionDaoImpl;
import vn.myclass.core.daoimpl.ListenGuideLineDaoImpl;
import vn.myclass.core.daoimpl.ResultDaoImpl;
import vn.myclass.core.daoimpl.RoleDaoImpl;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.serviceimpl.ResultServiceImpl;

public class SingletonDaoUtil {
	private static UserDaoImpl userDaoImpl = null;
	private static RoleDaoImpl roleDaoImpl = null;
	private static ListenGuideLineDaoImpl listenGuideLineDaoImpl = null;
	private static ExaminationDaoImpl examinationDaoImpl = null;
	private static ExaminationQuestionDaoImpl examinationQuestionDaoImpl = null;
	private static ExerciseDaoImpl exerciseDaoImpl = null;
	private static ExerciseQuestionDaoImpl exerciseQuestionDaoImpl = null;
	private static CommentDaoImpl commentDaoImpl = null;
	private static ResultDaoImpl resultDaoImpl = null;
	
	public static UserDaoImpl getUserDaoImplInstance() {
		if(userDaoImpl == null) {
			userDaoImpl = new UserDaoImpl();
		}
		return userDaoImpl;
	}
	
	public static RoleDaoImpl getRoleDaoImplInstance() {
		if(roleDaoImpl == null) {
			roleDaoImpl = new RoleDaoImpl();
		}
		return roleDaoImpl;
	}
	
	public static ListenGuideLineDaoImpl getListenGuideLineDaoImplInstance() {
		if(listenGuideLineDaoImpl == null) {
			listenGuideLineDaoImpl = new ListenGuideLineDaoImpl();
		}
		return listenGuideLineDaoImpl;
	}
	
	public static ExaminationDaoImpl getExaminationDaoImplInstance() {
		if(examinationDaoImpl == null) {
			examinationDaoImpl = new ExaminationDaoImpl();
		}
		return examinationDaoImpl;
	}
	
	public static ExaminationQuestionDaoImpl getExaminationQuestionDaoImplInstance() {
		if(examinationQuestionDaoImpl == null) {
			examinationQuestionDaoImpl = new ExaminationQuestionDaoImpl();
		}
		return examinationQuestionDaoImpl;
	}
	

	public static ExerciseDaoImpl getExerciseDaoImplInstance() {
		if(exerciseDaoImpl == null) {
			exerciseDaoImpl = new ExerciseDaoImpl();
		}
		return exerciseDaoImpl;
	}
	
	
	public static ExerciseQuestionDaoImpl getExerciseQuestionDaoImplInstance() {
		if(exerciseQuestionDaoImpl == null) {
			exerciseQuestionDaoImpl = new ExerciseQuestionDaoImpl();
		}
		return exerciseQuestionDaoImpl;
	}
	

	public static CommentDaoImpl getCommentDaoImplInstance() {
		if(commentDaoImpl == null) {
			commentDaoImpl = new CommentDaoImpl();
		}
		return commentDaoImpl;
	}
	
	public static ResultDaoImpl getResultDaoImplInstance() {
		if(resultDaoImpl == null) {
			resultDaoImpl = new ResultDaoImpl();
		}
		return resultDaoImpl;
	}
}
