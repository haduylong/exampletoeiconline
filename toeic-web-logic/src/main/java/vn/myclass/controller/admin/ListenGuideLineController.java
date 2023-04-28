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
import vn.myclass.core.web.utils.WebCommonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import vn.myclass.core.service.ListenGuideLineService;
import vn.myclass.core.serviceimpl.ListenGuideLineServiceImpl;
import vn.myclass.core.utils.ListenGuideLineBeanUtil;

@WebServlet(urlPatterns = { "/admin-guideline-listen-list.html", "/admin-guideline-listen-edit.html"})
public class ListenGuideLineController extends HttpServlet {
	private final Logger log = Logger.getLogger(this.getClass());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, req);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");	
		///
		if(command.getUrlType()!=null && command.getUrlType().equalsIgnoreCase(WebConstant.URL_LIST)) {// tìm kiếm or show list
			if(command.getCrudaction()!=null && command.getCrudaction().equals(WebConstant.REDIRECT_DELETE)) { // xóa
				List<Integer> ids = new ArrayList<>();
				for(String item : command.getCheckList()) {
					ids.add(Integer.parseInt(item));
				}				
				Integer result = SingletonServiceUtil.getListenGuideLineServiceImplInstance().delete(ids);
				if(result != ids.size()) {
					command.setCrudaction(WebConstant.REDIRECT_ERROR);
				}
			}
			
			excuteSearchListenGuideline(req, command); // tìm kiếm ds
			if (command.getCrudaction() != null) {
	                Map<String, String> mapMessage = buidMapRedirectMessage(resourceBundle);
	                WebCommonUtil.addRedirectMessage(req, command.getCrudaction(), mapMessage);
	          }
			req.setAttribute(WebConstant.LIST_ITEMS, command);
			req.getRequestDispatcher("/views/admin/listenguideline/list.jsp").forward(req, resp);
		}else if(command.getUrlType()!=null && command.getUrlType().equalsIgnoreCase(WebConstant.URL_EDIT)) {
			if(command.getPojo()!=null && command.getPojo().getListenGuideLineId()!=null) {
				command.setPojo(SingletonServiceUtil.getListenGuideLineServiceImplInstance().findByListenGuideLineId("listenGuideLineId", command.getPojo().getListenGuideLineId()));
			}
			req.setAttribute(WebConstant.FORM_ITEM, command);
			req.getRequestDispatcher("/views/admin/listenguideline/edit.jsp").forward(req, resp);
		}
		
	}
	
	
	private Map<String, String> buidMapRedirectMessage(ResourceBundle resourceBundle) {
		 Map<String, String> mapMessage = new HashMap<String, String>();
	     mapMessage.put(WebConstant.REDIRECT_INSERT, resourceBundle.getString("label.listenguideline.add.success"));
	     mapMessage.put(WebConstant.REDIRECT_UPDATE, resourceBundle.getString("label.listenguideline.update.success"));
	     mapMessage.put(WebConstant.REDIRECT_DELETE, resourceBundle.getString("label.listenguideline.delete.success"));
	     mapMessage.put(WebConstant.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
	     return mapMessage;
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
		Set<String> set = buildSetValueListenGuideLine();
		Object[] ob =  uploadUtil.writeOrUpdateFile(req, set, WebConstant.LISTENGUIDELINE);
		boolean checkStatusUploadImage = (Boolean) ob[0];
		try {
			if(!checkStatusUploadImage) {
				resp.sendRedirect("./admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_error");
			}else {
				ListenGuideLineDTO dto = command.getPojo();
				if(StringUtils.isNotBlank(ob[2].toString())) {// kiểm tra nếu ảnh ko bị blank thì gán ảnh vào dto (vì khi update thì ko gửi file ảnh lên)
					dto.setImage(ob[2].toString());
				}
				Map<String, String> mapValue = (Map<String, String>) ob[3];
				dto = returnValueOfDTO(dto, mapValue);
				if(dto != null) {
					if(dto.getListenGuideLineId()!=null) {
						// update
						ListenGuideLineDTO listenGuideLineDTO = SingletonServiceUtil.getListenGuideLineServiceImplInstance().findByListenGuideLineId("listenGuideLineId", dto.getListenGuideLineId());
						if(dto.getImage() == null) {// image bị blank thì gán image dưới database vào dto
							dto.setImage(listenGuideLineDTO.getImage());
							dto.setCreatedDate(listenGuideLineDTO.getCreatedDate());
						}
						ListenGuideLineDTO result = SingletonServiceUtil.getListenGuideLineServiceImplInstance().updateLiGuideLine(dto);
						if(result != null) {
							resp.sendRedirect("./admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_update");
						}else {
							resp.sendRedirect("./admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_error");
						}
					}else {
						//save
						try {
							SingletonServiceUtil.getListenGuideLineServiceImplInstance().saveListenGuideLine(dto);
							resp.sendRedirect("./admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_insert");
						} catch (ConstraintViolationException e) {
							 log.error(e.getMessage(), e);
		                     resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_error");
							
						}
						
					}
				}
				
			}			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp.sendRedirect("./admin-guideline-listen-list.html?urlType=url_list");
		}
		
	}


	private ListenGuideLineDTO returnValueOfDTO(ListenGuideLineDTO dto, Map<String, String> mapValue) {
		for (Map.Entry<String, String> item: mapValue.entrySet()) {
            if (item.getKey().equals("pojo.title")) {
                dto.setTitle(item.getValue());
            } else if (item.getKey().equals("pojo.content")) {
                dto.setContent(item.getValue());
            } else if (item.getKey().equals("pojo.listenGuideLineId")) {
                dto.setListenGuideLineId(Integer.parseInt(item.getValue().toString()));
            }
        }
        return dto;
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
		set.add("pojo.listenGuideLineId");
		return set;
	}
	
	
}
