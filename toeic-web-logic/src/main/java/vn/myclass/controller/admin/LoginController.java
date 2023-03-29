package vn.myclass.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import vn.myclass.command.UserCommand;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.service.UserService;
import vn.myclass.core.serviceimpl.UserServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;

@WebServlet("/login.html")
public class LoginController extends HttpServlet {
	private final Logger log = Logger.getLogger(this.getClass());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy tất cả các tham số lên cùng 1 lúc
		UserCommand command = FormUtil.populate(UserCommand.class, req);
		UserDTO pojo = command.getPojo();
		UserService userService = new UserServiceImpl();
		if(userService.isUserExist(pojo) != null) {
			if(userService.findRoleByUser(pojo).getRoleDTO().getName().equalsIgnoreCase(WebConstant.ROLE_ADMIN)) {
				System.out.println("ADMIN");
				//req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
				resp.sendRedirect("admin-home.html");
			}else {
				req.getRequestDispatcher("/views/web/home.jsp").forward(req, resp);
			}
		}else{
			System.out.println("fail");
			req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
			req.setAttribute(WebConstant.MESSAGE_RESPONSE, "Ten dang nhap hoac mat khau sai");
			req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
			System.out.println("fail");
		}
	}
}

