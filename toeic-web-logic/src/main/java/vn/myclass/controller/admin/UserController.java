package vn.myclass.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.myclass.command.UserCommand;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.service.UserService;
import vn.myclass.core.serviceimpl.UserServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.RequestUtil;

@WebServlet(urlPatterns = {"/admin-user-list.html"})
public class UserController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, req); // lấy UrlType từ menu.jsp
		UserService userService = new UserServiceImpl();
		
		Map<String, Object> map = new HashMap<>();
		command.setMaxPageItems(5);
		// hỗ trợ phân trang bằng cách set các thuộc tính cho command 
		RequestUtil.initSearchBean(req, command); // SortExpression, SortDirection, page, FirstItem
		//
		Object[] ob = userService.findByProperty(map, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
		command.setListResult((List<UserDTO>)ob[1]);
		command.setTotalItems(Integer.parseInt(ob[0].toString()));
		
		if(command.getUrlType().equalsIgnoreCase(WebConstant.URL_LIST)) {
			
			req.setAttribute(WebConstant.LIST_ITEMS, command);
			req.getRequestDispatcher("/views/admin/user/list.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
