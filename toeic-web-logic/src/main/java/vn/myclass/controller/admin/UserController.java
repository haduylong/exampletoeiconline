package vn.myclass.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import vn.myclass.command.UserCommand;
import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.service.RoleService;
import vn.myclass.core.service.UserService;
import vn.myclass.core.serviceimpl.RoleServiceImpl;
import vn.myclass.core.serviceimpl.UserServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.RequestUtil;
import vn.myclass.core.web.utils.WebCommonUtil;

@WebServlet(urlPatterns = {"/admin-user-list.html", "/ajax-admin-user-edit.html"})
public class UserController extends HttpServlet {
	private final Logger log = Logger.getLogger(this.getClass());
	UserService userService = new UserServiceImpl();
	RoleService roleService = new RoleServiceImpl();
	ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, req); // lấy UrlType từ menu.jsp
		UserDTO pojo = command.getPojo();
		
		if(command.getUrlType().equalsIgnoreCase(WebConstant.URL_LIST)) {// gọi trang list
			Map<String, Object> map = new HashMap<>();
			command.setMaxPageItems(5);
			// hỗ trợ phân trang bằng cách set các thuộc tính cho command 
			RequestUtil.initSearchBean(req, command); // SortExpression, SortDirection, page, FirstItem

			Object[] ob = userService.findByProperty(map, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
			command.setListResult((List<UserDTO>)ob[1]);
			command.setTotalItems(Integer.parseInt(ob[0].toString()));
			
			if(command.getCrudaction()!= null) {
				Map<String, String> mapMessage = buildMapRedirectMessage(bundle);
				WebCommonUtil.addRedirectMessage(req, command.getCrudaction(), mapMessage);
			}
			
			req.setAttribute(WebConstant.LIST_ITEMS, command);
			req.getRequestDispatcher("/views/admin/user/list.jsp").forward(req, resp);
		}else if(command.getUrlType().equalsIgnoreCase(WebConstant.URL_EDIT)) {// gọi trang edit
			if(command.getCrudaction()!= null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
				req.setAttribute(WebConstant.MESSAGE_RESPONSE, "insert_success");
			}else if(pojo != null && pojo.getUserId() != null) {
				pojo = userService.findById(pojo.getUserId());
				command.setRoles(roleService.findAll());
				command.setPojo(pojo);
			}else {
				command.setRoles(roleService.findAll());
			}
			req.setAttribute(WebConstant.FORM_ITEM, command);
			req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);
		}
		
	}
	
	private Map<String, String> buildMapRedirectMessage(ResourceBundle bundle2) {
		Map<String, String> mapMessage = new HashMap<>();
		mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.user.message.add.success"));
		mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.user.message.update.success"));
		mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.user.message.delete.success"));
		mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
		return mapMessage;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, req);
		try {
			if(command.getUrlType().equalsIgnoreCase(WebConstant.URL_EDIT)) {
				if(command.getCrudaction()!= null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
					UserDTO pojo = command.getPojo();
					
					RoleDTO roleDTO = new RoleDTO();
					roleDTO.setRoleId(command.getRoleId());
					pojo.setRoleDTO(roleDTO);
					if(pojo!=null && pojo.getUserId()!=null) {
						// update user
						userService.updateUser(pojo);
						req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
					}else {
						// save user
						userService.saveUser(pojo);
						req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
		}
		
		
		req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);
	}
}
