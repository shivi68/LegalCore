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


@WebServlet("/AdvocateRegister")
@MultipartConfig
public class AdvocateRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			String address=request.getParameter("address");
			int experience=Integer.parseInt(request.getParameter("experience"));
			String password=request.getParameter("password");
			Part p=request.getPart("photo");
			String category=request.getParameter("category");
			InputStream photo=null;
			if(p!=null) {
				photo=p.getInputStream();
			}
			HashMap advocate=new HashMap();
			advocate.put("email",email);
			advocate.put("name",name);
			advocate.put("phone",phone);
			advocate.put("address",address);
			advocate.put("photo",photo);
			advocate.put("password",password);
			advocate.put("experience",experience);
			advocate.put("category",category);
			DAO d=new DAO();
			String result=d.registerAdvocate(advocate);
			if(result.equalsIgnoreCase("success")) {
				HttpSession session=request.getSession();
				session.setAttribute("name",name);
				session.setAttribute("email",email);
				response.sendRedirect("AdvocateHome.jsp");
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("msg","Email Id Already Exist!");
				response.sendRedirect("Advocate.jsp");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
