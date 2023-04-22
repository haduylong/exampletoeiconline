package vn.myclass.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import vn.myclass.command.ListenGuidelineCommand;
import vn.myclass.core.dto.ListenGuideLineDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.RequestUtil;
import vn.myclass.core.web.utils.SingletonServiceUtil;
import vn.myclass.core.web.utils.UploadUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import vn.myclass.core.service.ListenGuideLineService;
import vn.myclass.core.serviceimpl.ListenGuideLineServiceImpl;
import vn.myclass.core.utils.ListenGuideLineBeanUtil;

@WebServlet(urlPatterns = { "/admin-guideline-listen-list.html", "/admin-guideline-listen-edit.html"})
public class ListenGuideLineController extends HttpServlet {
	ListenGuideLineService listenGuideLineService = new ListenGuideLineServiceImpl();
	private final Logger log = Logger.getLogger(this.getClass());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, req);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
		HttpSession session = req.getSession();
		
//		if(session != null) {
//			req.setAttribute(WebConstant.ALERT, session.getAttribute(WebConstant.ALERT));
//			req.setAttribute(WebConstant.MESSAGE_RESPONSE, session.getAttribute(WebConstant.MESSAGE_RESPONSE));
//		}
//		// 
//		req.setAttribute(WebConstant.LIST_ITEMS, command);
		///
		if(command.getUrlType()!=null && command.getUrlType().equalsIgnoreCase(WebConstant.URL_EDIT)) {
			req.getRequestDispatcher("/views/admin/listenguideline/edit.jsp").forward(req, resp);
		}else if(command.getUrlType()!=null && command.getUrlType().equalsIgnoreCase(WebConstant.URL_LIST)) {
			excuteSearchListenGuideline(req, command); // tìm kiếm 
			req.setAttribute(WebConstant.LIST_ITEMS, command);
			req.getRequestDispatcher("/views/admin/listenguideline/list.jsp").forward(req, resp);
		}
		
//		session.removeAttribute(WebConstant.ALERT);
//		session.removeAttribute(WebConstant.MESSAGE_RESPONSE);
	}
	
	
	private void excuteSearchListenGuideline(HttpServletRequest req, ListenGuidelineCommand command) {
//		/// tao list dto	
//		// set các thuộc tính phân trang
//		command.setMaxPageItems(3);
		// hỗ trợ phân trang bằng cách set các thuộc tính cho command 
		RequestUtil.initSearchBean(req, command); // SortExpression, SortDirection, page, FirstItem
//		// lấy các dto lên theo đúng yêu cầu phân trang
		Map<String, Object> mapProperties = buildMapProperties(command);
		Object[] objects = SingletonServiceUtil.getListenGuideLineServiceImplInstance().findByProperty(mapProperties, command.getSortExpression(), command.getSortDirection(), 
																command.getFirstItem(), command.getMaxPageItems());
		
		command.setListResult((List<ListenGuideLineDTO>)objects[1]);
		
		command.setTotalItems(Integer.parseInt(objects[0].toString()));		
	}
	


	private Map<String, Object> buildMapProperties(ListenGuidelineCommand command) {
		Map<String, Object> properties = new HashMap<>();
		if(StringUtils.isNotBlank(command.getPojo().getTitle())) {
			properties.put("title", command.getPojo().getTitle());
		}
		return properties;
	}


	/*                                        DO_POST                                                              */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, req);
		ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
		UploadUtil uploadUtil = new UploadUtil();
		HttpSession session = req.getSession();// HttpSession chuyen giua cac controller ma ko mat gia tri
		
		Set<String> set = buildSetValueListenGuideLine();
		
		try {
			Object[] ob =  uploadUtil.writeOrUpdateFile(req, set, WebConstant.LISTENGUIDELINE);
			Map<String, String> map = (Map<String, String>) ob[3];
			getCommandPojoFromMap(command, map, set);
			
			
			session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
			session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.guideline.listen.add.success"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			session.setAttribute(WebConstant.ALERT,  WebConstant.TYPE_ERROR);
			session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.error"));
		}
		
		resp.sendRedirect("./admin-guideline-listen-list.html?urlType=url_list");
	}


	private void getCommandPojoFromMap(ListenGuidelineCommand command, Map<String, String> map, Set<String> set) {
		for(String item : set) {// duyệt lấy tên pojo trong set
			if(item.equalsIgnoreCase("pojo.title")) {
				command.getPojo().setTitle(map.get(item));
			}else if(item.equalsIgnoreCase("pojo.content")){
				command.getPojo().setContent(map.get(item));
			}
		}
		
	}

	private Set<String> buildSetValueListenGuideLine() {
		Set<String> set = new HashSet<>();
		set.add("pojo.title");
		set.add("pojo.content");
		return set;
	}
	
	
}
