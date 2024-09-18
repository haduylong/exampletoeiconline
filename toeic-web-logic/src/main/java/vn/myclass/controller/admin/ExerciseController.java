package vn.myclass.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.UploadUtil;

@WebServlet(urlPatterns = {"/admin-exericse-upload.html"})
public class ExerciseController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/exercise/upload.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UploadUtil uploadUtil = new UploadUtil();
		Object[] objects = uploadUtil.writeOrUpdateFile(req, null, WebConstant.EXERCISE);
		req.getRequestDispatcher("/views/admin/exercise/upload.jsp").forward(req, resp);
	}
}
