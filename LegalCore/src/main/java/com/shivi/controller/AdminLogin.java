package com.shivi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shivi.model.DAO;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			DAO d=new DAO();
			String adminName=d.checkAdminLogin(id, password);
			if(adminName==null) {
				HttpSession session=request.getSession();
				session.setAttribute("msg","Id or Password is wrong!");
				response.sendRedirect("Admin.jsp");
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("adminName",adminName);
				response.sendRedirect("AdminHome.jsp");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
