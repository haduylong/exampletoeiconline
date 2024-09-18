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

import org.apache.commons.lang.StringUtils;

import vn.myclass.command.ExaminationCommand;
import vn.myclass.command.ExerciseCommand;
import vn.myclass.core.dto.ExaminationDTO;
import vn.myclass.core.dto.ExerciseDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.RequestUtil;
import vn.myclass.core.web.utils.SingletonServiceUtil;

@WebServlet(urlPatterns = {"/danh-sach-bai-thi.html"})
public class ExaminationController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ExaminationCommand command = FormUtil.populate(ExaminationCommand.class, req);
		excuteSearchListenGuideline(req, command);
		req.setAttribute(WebConstant.LIST_ITEMS, command);
		req.getRequestDispatcher("/views/web/examination/list.jsp").forward(req, resp);
	}
	private void excuteSearchListenGuideline(HttpServletRequest req, ExaminationCommand command) {
		Map<String, Object> properties = buildMapProperties(command);
		command.setMaxPageItems(3); // set max page item bằng tay
		RequestUtil.initSearchBeanManual(req, command);
		Object[] objects = SingletonServiceUtil.getExaminationServiceImplInstance().findByProperty(properties, command.getSortExpression(), command.getSortDirection(), 
				command.getFirstItem(), command.getMaxPageItems());
		command.setListResult((List<ExaminationDTO>) objects[1]);
		command.setTotalItems((int) objects[0]);
		double totalPage = (double) command.getTotalItems() / command.getMaxPageItems();
		command.setTotalPages((int)Math.ceil(totalPage)); // total page = max item / max item in page
	}

	private Map<String, Object> buildMapProperties(ExaminationCommand command) {
		Map<String, Object> properties = new HashMap<>();
		if(StringUtils.isNotBlank(command.getPojo().getName())) {
			properties.put("name", command.getPojo().getName());
		}
		return properties;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
