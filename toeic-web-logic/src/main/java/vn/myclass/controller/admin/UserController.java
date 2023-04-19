package vn.myclass.controller.admin;

import java.io.File;
import java.io.FileInputStream;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vn.myclass.command.UserCommand;
import vn.myclass.core.common.utils.ExcelPoiUtil;
import vn.myclass.core.common.utils.SessionUtil;
import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.dto.UserImportDTO;
import vn.myclass.core.service.RoleService;
import vn.myclass.core.service.UserService;
import vn.myclass.core.serviceimpl.RoleServiceImpl;
import vn.myclass.core.serviceimpl.UserServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.RequestUtil;
import vn.myclass.core.web.utils.SingletonServiceUtil;
import vn.myclass.core.web.utils.UploadUtil;
import vn.myclass.core.web.utils.WebCommonUtil;

@WebServlet(urlPatterns = {"/admin-user-list.html", "/ajax-admin-user-edit.html",
							"/admin-user-import.html", "/admin-user-import-validate.html"})
public class UserController extends HttpServlet {
	private final Logger log = Logger.getLogger(this.getClass());
	private final String SHOW_IMPORT_USER = "show_import_user";
	private final String READ_EXCEL = "read_excel";
	private final String VALIDATE_IMPORT = "validate_import";
	private final String LIST_USER_IMPORT = "list_user_import";
	ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, req); // lấy UrlType từ menu.jsp
		UserDTO pojo = command.getPojo();
		
		if(command.getUrlType().equalsIgnoreCase(WebConstant.URL_LIST)) {// gọi trang list
			Map<String, Object> map = new HashMap<>();
			command.setMaxPageItems(5);
			RequestUtil.initSearchBean(req, command); //hỗ trợ phân trang bằng cách set các thuộc tính cho command  SortExpression, SortDirection, page, FirstItem

			Object[] ob = SingletonServiceUtil.getUserServiceImplInstance().findByProperty(map, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
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
				pojo = SingletonServiceUtil.getUserServiceImplInstance().findById(pojo.getUserId());
				command.setRoles(SingletonServiceUtil.getRoleServiceImplInstance().findAll());
				command.setPojo(pojo);
			}else {
				command.setRoles(SingletonServiceUtil.getRoleServiceImplInstance().findAll());
			}
			req.setAttribute(WebConstant.FORM_ITEM, command);
			req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);
		}else if(command.getUrlType().equalsIgnoreCase(SHOW_IMPORT_USER)) {
			req.getRequestDispatcher("/views/admin/user/importuser.jsp").forward(req, resp);
		}else if(command.getUrlType().equalsIgnoreCase(VALIDATE_IMPORT)) {
			List<UserImportDTO> userImportDTOs = (List<UserImportDTO>) SessionUtil.getInstance().getValue(req, LIST_USER_IMPORT);
			command.setUserImportDTOs(userImportDTOs);
			command.setMaxPageItems(5);
			command.setTotalItems(userImportDTOs.size());
			req.setAttribute(WebConstant.LIST_ITEMS, command); // gửi xuống để hiển thị
			req.getRequestDispatcher("/views/admin/user/importuser.jsp").forward(req, resp);
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
		UploadUtil uploadUtil = new UploadUtil();
		Set<String> setValue = new HashSet<>();
		setValue.add("urlType");
		Object[] objects = uploadUtil.writeOrUpdateFile(req, setValue, "excel");
		try {
			UserCommand command = FormUtil.populate(UserCommand.class, req);
			// -, vào page eidt
			if(command.getUrlType()!=null && command.getUrlType().equalsIgnoreCase(WebConstant.URL_EDIT)) {
				if(command.getCrudaction()!= null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
					UserDTO pojo = command.getPojo();
					
					RoleDTO roleDTO = new RoleDTO();
					roleDTO.setRoleId(command.getRoleId());
					pojo.setRoleDTO(roleDTO);
					if(pojo!=null && pojo.getUserId()!=null) {
						// update user
						SingletonServiceUtil.getUserServiceImplInstance().updateUser(pojo);
						req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
					}else {
						// save user
						SingletonServiceUtil.getUserServiceImplInstance().saveUser(pojo);
						req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
					}
				}
				req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);
			}
			//-, đọc file
			if(objects!=null) {
				String urlType = null;
				Map<String, String> mapValue = (Map<String, String>) objects[3];
				for(Map.Entry<String, String> item : mapValue.entrySet()) {
					if(item.getKey().equals("urlType")) {
						urlType = item.getValue();
					}
				}
				
				if(urlType!=null && urlType.equals(READ_EXCEL)) {
					String fileLocation = objects[1].toString();
					String fileName = objects[2].toString();				
					List<UserImportDTO> excelValues = returnValuesFromExcel(fileName, fileLocation);
					// validate
					validateData(excelValues);
					SessionUtil.getInstance().putValue(req, LIST_USER_IMPORT , excelValues); // put value into session
					resp.sendRedirect("./admin-user-import-validate.html?urlType=validate_import");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
		}
		
		
	}

	private void validateData(List<UserImportDTO> excelValues) {
		Set<String> stringSet = new HashSet<>();
		for(UserImportDTO item : excelValues) {
			validateRequireField(item);
			validateDuplicate(item, stringSet);
			SingletonServiceUtil.getUserServiceImplInstance().validateImportUser(excelValues);
		}
		
	}

	private void validateDuplicate(UserImportDTO item, Set<String> stringSet) {
		String message = item.getError();
			if(!stringSet.contains(item.getUserName())){
				stringSet.add(item.getUserName());
			}else {
				if(item.isValid()) {// truoc do ko co loi
					message += "<br/>";
					message += bundle.getString("label.username.duplicate");
				}
			}
			if(StringUtils.isNotBlank(message)) {
				item.setValid(false);
				item.setError(message);	
			}
	}

	private void validateRequireField(UserImportDTO item) {
		String message = "";
		if(StringUtils.isBlank(item.getUserName())) {
			message += "<br/>";
			message += bundle.getString("label.username.notempty");
		}
		if(StringUtils.isBlank(item.getPassword())) {
			message += "<br/>";
			message += bundle.getString("label.password.notempty");
		}
		if(StringUtils.isBlank(item.getRoleName())){
			message += "<br/>";
			message += bundle.getString("label.rolename.notempty");
		}
		
		if(StringUtils.isBlank(message)) {
			item.setValid(true);
		}
		
		item.setError(message);
		
		
	}

	private List<UserImportDTO> returnValuesFromExcel(String fileName, String fileLocation) throws IOException {
		Workbook workbook = ExcelPoiUtil.getWorkbook(fileName, fileLocation); 
		Sheet sheet = workbook.getSheetAt(0);
		List<UserImportDTO> excelValues = new ArrayList<>();
		for(int i=1; i<=sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			UserImportDTO userImportDTO = readDataFromExcel(row);
			excelValues.add(userImportDTO);
		}
		return excelValues;
	}

	private UserImportDTO readDataFromExcel(Row row) {
		UserImportDTO userImportDTO = new UserImportDTO();
		userImportDTO .setUserName(ExcelPoiUtil.getCellValue(row.getCell(0)));
		userImportDTO.setPassword(ExcelPoiUtil.getCellValue(row.getCell(1)));
		userImportDTO.setFullName(ExcelPoiUtil.getCellValue(row.getCell(2)));
		userImportDTO.setRoleName(ExcelPoiUtil.getCellValue(row.getCell(3)));
		return userImportDTO;
	}
	
	
	
}
