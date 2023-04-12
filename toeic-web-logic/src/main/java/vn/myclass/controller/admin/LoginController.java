package vn.myclass.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import vn.myclass.command.UserCommand;
import vn.myclass.core.dto.CheckLogin;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.service.UserService;
import vn.myclass.core.serviceimpl.UserServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.SingletonServiceUtil;

@WebServlet("/login.html")
public class LoginController extends HttpServlet {
	private final Logger log = Logger.getLogger(this.getClass());
	ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy tất cả các tham số lên cùng 1 lúc
		UserCommand command = FormUtil.populate(UserCommand.class, req);
		UserDTO pojo = command.getPojo();
		if(pojo!=null) {
			CheckLogin login = SingletonServiceUtil.getUserServiceImplInstance().checkLogin(pojo.getName(), pojo.getPassWord());
			if(login.isUserExist()) {
				if(login.getRoleName().equals(WebConstant.ROLE_ADMIN)) {
					resp.sendRedirect("admin-home.html");
				}else if(login.getRoleName().equals(WebConstant.ROLE_USER)) {
					resp.sendRedirect("home.html");
				}
			}else {
				req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
				req.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.name.password.wrong"));
				req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
			}
		}

	}
}

