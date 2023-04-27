package vn.myclass.core.web.utils;

import vn.myclass.core.serviceimpl.CommentServiceImpl;
import vn.myclass.core.serviceimpl.ExaminationQuestionServiceImpl;
import vn.myclass.core.serviceimpl.ExaminationServiceImpl;
import vn.myclass.core.serviceimpl.ExerciseQuestionServiceImpl;
import vn.myclass.core.serviceimpl.ExerciseServiceImpl;
import vn.myclass.core.serviceimpl.ListenGuideLineServiceImpl;
import vn.myclass.core.serviceimpl.RoleServiceImpl;
import vn.myclass.core.serviceimpl.UserServiceImpl;

public class SingletonServiceUtil {
	private static UserServiceImpl userServiceImpl = null;
	private static RoleServiceImpl roleServiceImpl = null;
	private static ListenGuideLineServiceImpl listenGuideLineServiceImpl = null;
	private static ExaminationServiceImpl examinationServiceImpl = null;
	private static ExaminationQuestionServiceImpl examinationQuestionServiceImpl = null;
	private static ExerciseServiceImpl exerciseServiceImpl = null;
	private static ExerciseQuestionServiceImpl exerciseQuestionServiceImpl = null;
	private static CommentServiceImpl commentServiceImpl = null;
	
	public static UserServiceImpl getUserServiceImplInstance() {
		if(userServiceImpl == null) {
			userServiceImpl = new UserServiceImpl();
		}
		return userServiceImpl;
	}
	
	public static RoleServiceImpl getRoleServiceImplInstance() {
		if(roleServiceImpl == null) {
			roleServiceImpl = new RoleServiceImpl();
		}
		return roleServiceImpl;
	}
	
	public static ListenGuideLineServiceImpl getListenGuideLineServiceImplInstance() {
		if(listenGuideLineServiceImpl == null) {
			listenGuideLineServiceImpl = new ListenGuideLineServiceImpl();
		}
		return listenGuideLineServiceImpl;
	}
	
	public static ExaminationServiceImpl getExaminationServiceImplInstance() {
		if(examinationServiceImpl == null) {
			examinationServiceImpl = new ExaminationServiceImpl();
		}
		return examinationServiceImpl;
	}
	
	public static ExaminationQuestionServiceImpl getExaminationQuestionServiceImplInstance() {
		if(examinationQuestionServiceImpl == null) {
			examinationQuestionServiceImpl = new ExaminationQuestionServiceImpl();
		}
		return examinationQuestionServiceImpl;
	}
	
	public static ExerciseServiceImpl getExerciseServiceImplInstance() {
		if(exerciseServiceImpl == null) {
			exerciseServiceImpl = new ExerciseServiceImpl();
		}
		return exerciseServiceImpl;
	}
	
	public static ExerciseQuestionServiceImpl getExerciseQuestionServiceImplInstance() {
		if(exerciseQuestionServiceImpl == null) {
			exerciseQuestionServiceImpl = new ExerciseQuestionServiceImpl();
		}
		return exerciseQuestionServiceImpl;
	}
	
	public static CommentServiceImpl getCommentServiceImplInstance() {
		if(commentServiceImpl == null) {
			commentServiceImpl = new CommentServiceImpl();
		}
		return commentServiceImpl;
	}
}
