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
import vn.myclass.core.common.utils.SessionUtil;
import vn.myclass.core.dto.ExaminationQuestionDTO;
import vn.myclass.core.dto.ResultDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.SingletonServiceUtil;

@WebServlet(urlPatterns = {"/bai-thi-thuc-hanh.html", "/bai-thi-dap-an.html"})
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
		// set dap an cua nguoi dung cho cau hoi
		ExaminationQuestionCommand command = new ExaminationQuestionCommand();
		command.setExaminationId(Integer.parseInt(req.getParameter("examinationId")));
		getListExaminationQuestion(command);
		for(ExaminationQuestionDTO item : command.getListResult()) {
			if(req.getParameter("answerUser["+item.getExaminationQuestionId()+"]") != null) {
				item.setAnswerUser(req.getParameter("answerUser["+item.getExaminationQuestionId()+"]"));
			}
		}
		// luu thong tin ket qua thi cua nguoi dung vao results
		String userName = (String) SessionUtil.getInstance().getValue(req, WebConstant.LOGIN_NAME);
		ResultDTO resultDTO = SingletonServiceUtil.getResultServiceImplInstance().saveResult(userName, command.getExaminationId(), command.getListResult());
		// so cau nghe dung va so cau doc dung
		command.setListenScore(resultDTO.getListenScore());
		command.setReadingScore(resultDTO.getReadingScore());
		req.setAttribute(WebConstant.LIST_ITEMS, command);
		req.getRequestDispatcher("/views/web/examination/result.jsp").forward(req, resp);
	}
}
