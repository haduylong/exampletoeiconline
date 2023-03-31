package vn.myclass.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import vn.myclass.command.ListenGuidelineCommand;
import vn.myclass.core.dto.ListenGuideLineDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.RequestUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import vn.myclass.core.service.ListenGuideLineService;
import vn.myclass.core.serviceimpl.ListenGuideLineServiceImpl;
import vn.myclass.core.utils.ListenGuideLineBeanUtil;

@WebServlet("/admin-guideline-listen-list.html")
public class ListenGuideLineController extends HttpServlet {
	ListenGuideLineService listenGuideLineService = new ListenGuideLineServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ListenGuidelineCommand command = new ListenGuidelineCommand();
		/// tao list dto	
		// set các thuộc tính phân trang
		command.setMaxPageItems(3);
		// hỗ trợ phân trang bằng cách set các thuộc tính cho command
		RequestUtil.initSearchBean(req, command); // SortExpression, SortDirection, page, FirstItem
		// lấy các dto lên theo đúng yêu cầu phân trang
		Object[] objects = listenGuideLineService.findByProperty(null, null, command.getSortExpression(), command.getSortDirection(), 
																command.getFirstItem(), command.getMaxPageItems());
		
		command.setListResult((List<ListenGuideLineDTO>)objects[1]);
		
		// command.setTotalItems(Integer.parseInt(objects[0].toString()));
		command.setTotalItems(Integer.parseInt(objects[0].toString()));
		// 
		req.setAttribute(WebConstant.LIST_ITEMS, command);
		///
		req.getRequestDispatcher("/views/admin/listenguideline/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
