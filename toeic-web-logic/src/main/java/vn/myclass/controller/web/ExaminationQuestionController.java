package vn.myclass.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.myclass.command.ExaminationQuestionCommand;
import vn.myclass.core.dto.ExaminationQuestionDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.SingletonServiceUtil;

@WebServlet(urlPatterns = {"/bai-thi-thuc-hanh.html"})
public class ExaminationQuestionController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ExaminationQuestionCommand command = FormUtil.populate(ExaminationQuestionCommand.class, req);
		getListExaminationQuestion(command);
		req.setAttribute(WebConstant.LIST_ITEMS, command);
		req.getRequestDispatcher("/views/web/examination/detail.jsp").forward(req, resp);
	}
	
	private void getListExaminationQuestion(ExaminationQuestionCommand command) {
		Object[] objects = SingletonServiceUtil.getExaminationQuestionServiceImplInstance().findExaminationQuestionByProperties(new HashMap<String, Object>(), command.getSortExpression(), command.getSortDirection(), null, null, command.getExaminationId());
		command.setListResult((List<ExaminationQuestionDTO>) objects[1]);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
