package vn.myclass.core.web.utils;

import java.io.File;
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

import com.mysql.cj.util.StringUtils;

public class UploadUtil {
	private final int maxMemorySize = 3 * 1024 * 1024; // 3MB
	private final int maxRequestSize = 50 * 1024 * 1024; // 50MB
	
	// request : request
	// Set<String> titleValue : tên thuộc tính pojo truyền từ form (name)
	// path : đường dẫn muốn lưu
	
	public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValue, String path) throws FileUploadException, Exception {
		// cải tiến
		// nơi lưu trong image
		ServletContext context = request.getServletContext();
		String address = context.getRealPath("image");
		boolean check = true; // kiem tra co upload dc ko
		String localName = null;// link den file
		String name = null;// ten file
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
		List<FileItem> items = upload.parseRequest(request);
		for(FileItem item : items) {
			if(!item.isFormField()) {// nếu item là file
				String fileName = item.getName(); /* lấy tên */ name = fileName;
				if(org.apache.commons.lang.StringUtils.isNotBlank(name)) {
					File uploadedFile = new File(address + File.separator + path + File.separator + fileName);// file den duong dan 
					localName = address + File.separator + path + File.separator;
								
					boolean isExit = uploadedFile.exists(); // kiểm tra tồn tại
					if(isExit) {
						if(uploadedFile.delete()) {// kiểm tra xóa thành công hay ko
							item.write(uploadedFile);
						}else {
							check = false;
						}
					}else {
						item.write(uploadedFile);
					}
				}
			}else {// nếu item ko là file
				String nameField = item.getFieldName();
				String valueField = item.getString();
				if(titleValue.contains(nameField)) {
					mapReturnValue.put(nameField, valueField);
				}
			}
		}
		
		return new Object[] {check, localName, name, mapReturnValue};
	}
	
}
