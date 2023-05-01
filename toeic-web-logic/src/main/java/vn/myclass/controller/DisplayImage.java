package vn.myclass.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.myclass.core.web.common.WebConstant;
/*
	lấy foder or file từ bên ngoài project và ghi ra ngoiaf brownser
*/
public class DisplayImage extends HttpServlet {
	private final String imagesBase = "/" + WebConstant.FOLDER_UPLOAD;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imageUrl = req.getRequestURI();
		String relativeImagePath = imageUrl.substring(12); // imageUrl.substring("/repository/".length());
		ServletOutputStream outStream;
		outStream = resp.getOutputStream();
		FileInputStream fin = new FileInputStream(imagesBase + File.separator + relativeImagePath);
		BufferedInputStream bin = new BufferedInputStream(fin);
		BufferedOutputStream bout = new BufferedOutputStream(outStream);
		
		int ch =0; ;
		while((ch=bin.read())!=-1) {
			bout.write(ch);
		}
		
		bin.close();
		fin.close();
		bout.close();
		outStream.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
