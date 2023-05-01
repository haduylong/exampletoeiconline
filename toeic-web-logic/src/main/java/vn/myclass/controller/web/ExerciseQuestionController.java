package vn.myclass.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.myclass.command.ExerciseQuestionCommand;
import vn.myclass.core.dto.ExerciseQuestionDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.RequestUtil;
import vn.myclass.core.web.utils.SingletonServiceUtil;

@WebServlet(urlPatterns = {"/bai-tap-thuc-hanh.html", "/ajax-bai-tap-dap-an.html"})
public class ExerciseQuestionController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ExerciseQuestionCommand command = FormUtil.populate(ExerciseQuestionCommand.class, req);
		getListExerciseQuestion(command);
		req.setAttribute(WebConstant.LIST_ITEMS, command);
		req.getRequestDispatcher("/views/web/exercise/detail.jsp").forward(req, resp);
	}
	
	private void getListExerciseQuestion(ExerciseQuestionCommand command) {
		Map<String, Object> properties = buildMapProperties(command);
		command.setMaxPageItems(1);
		RequestUtil.initSearchBeanManual(null, command);
		Object[] objects = SingletonServiceUtil.getExerciseQuestionServiceImplInstance().findByProperty(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
		command.setListResult((List<ExerciseQuestionDTO>) objects[1]);
		command.setTotalItems((int) objects[0]);
		double totalPage = (double) command.getTotalItems() / command.getMaxPageItems();
		command.setTotalPages((int)Math.ceil(totalPage)); // total page = max item / max item in page
	}

	private Map<String, Object> buildMapProperties(ExerciseQuestionCommand command) {
		ExerciseQuestionDTO pojo = command.getPojo();
		Map<String, Object> properties = new HashMap<>();
		if(pojo.getExercise()!=null && pojo.getExercise().getExerciseId()!=null) {
			properties.put("exercise.exerciseId", pojo.getExercise().getExerciseId()); // bài exercise đọc hoặc nghe
		}
		return properties;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ExerciseQuestionCommand command = FormUtil.populate(ExerciseQuestionCommand.class, req);
		getListExerciseQuestion(command);
		for(ExerciseQuestionDTO item : command.getListResult()) {
			if(!command.getAnswerUser().equals(item.getCorrectAnswer())) {
				command.setCheckAnswer(true);
			};
		}
		req.setAttribute(WebConstant.LIST_ITEMS, command);
		req.getRequestDispatcher("/views/web/exercise/result.jsp").forward(req, resp);
	}
}
