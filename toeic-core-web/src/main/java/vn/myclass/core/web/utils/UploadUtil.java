package vn.myclass.core.web.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;



import vn.myclass.core.web.common.WebConstant;

public class UploadUtil {
	private final int maxMemorySize = 3 * 1024 * 1024; // 3MB
	private final int maxRequestSize = 50 * 1024 * 1024; // 50MB
	private final Logger log = Logger.getLogger(this.getClass());
	// request : request
	// Set<String> titleValue : tên thuộc tính pojo input hoặc tên input truyền từ form (name)
	// path : đường dẫn muốn lưu
	
	public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValue, String path) {
		// cải tiến
		// nơi lưu trong image
//		String address = "/" + WebConstant.FOLDER_UPLOAD;
//		String address = request.getServletContext().getRealPath("/" + WebConstant.FOLDER_UPLOAD);
		String address = "D:/Java/workspace_p1/exampletoeiconline/toeic-web/src/main/webapp/fileupload";
		checkAndCreateFolder(address, path);
		boolean check = true; // kiem tra co upload dc ko
		String localName = null;// link den file
		String fileName = null; // tên file 
		Map<String, String> mapReturnValue = new HashMap<>();// chua nhung values ko phai thuoc tinh file (vd <pojo.title, abc>)
		
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Set factory constraints
		factory.setSizeThreshold(maxMemorySize);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(maxRequestSize);

		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				if(!item.isFormField()) {// nếu item là file
					fileName = item.getName(); /* lấy tên */
					if(StringUtils.isNotBlank(fileName)) {
						File uploadedFile = new File(address + File.separator + path + File.separator + fileName);// file den duong dan 
						localName = address + File.separator + path + File.separator + fileName;
									
						boolean isExit = uploadedFile.exists(); // kiểm tra tồn tại
						try {
							if(isExit) {				
								uploadedFile.delete(); // kiểm tra xóa thành công hay ko
								item.write(uploadedFile);
								check = false;
							}else {
								item.write(uploadedFile);
							}
						} catch (Exception e) {
							check = false;
							log.error(e.getMessage(), e);
						}
						
					}
				}else {// nếu item ko là file
					String nameField = item.getFieldName();
					String valueField = item.getString("UTF-8");
					if(titleValue.contains(nameField)) {
						mapReturnValue.put(nameField, valueField);
					}
				}
			}
		} catch (FileUploadException e) {
			check = false;
			log.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}		
		
		String name = "";
		if(StringUtils.isNotBlank(fileName)) {
			name = path + File.separator + fileName;
		}
		
		return new Object[] {check, localName, name , mapReturnValue};
	}
	
	
	private void checkAndCreateFolder(String address, String path) {
		File folderRoot = new File(address);
		if(!folderRoot.exists()) {
			folderRoot.mkdirs();
		}
		File folderChild = new File(address + File.separator + path);
		if(!folderChild.exists()) {
			folderChild.mkdirs();
		}
	}
	
}
