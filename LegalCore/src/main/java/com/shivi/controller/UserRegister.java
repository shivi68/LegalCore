package com.shivi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.shivi.model.DAO;


@WebServlet("/UserRegister")
@MultipartConfig
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			String address=request.getParameter("address");
			String password=request.getParameter("password");
			Part p=request.getPart("photo");
			InputStream photo=null;
			if(p!=null) {
				photo=p.getInputStream();
			}
			HashMap user=new HashMap();
			user.put("email",email);
			user.put("name",name);
			user.put("phone",phone);
			user.put("address",address);
			user.put("photo",photo);
			user.put("password",password);
			DAO d=new DAO();
			String result=d.registerUser(user);
			if(result.equalsIgnoreCase("success")) {
				HttpSession session=request.getSession();
				session.setAttribute("name",name);
				session.setAttribute("email",email);
				response.sendRedirect("UserHome.jsp");
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("msg","Email Id Already Exist!");
				response.sendRedirect("User.jsp");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
