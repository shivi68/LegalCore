package com.shivi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shivi.model.DAO;


@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			DAO d=new DAO();
			String name=d.checkUserLogin(email, password);
			if(name==null) {
				HttpSession session=request.getSession();
				session.setAttribute("msg","Email-Id or Password is wrong!");
				response.sendRedirect("User.jsp");
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("name",name);
				session.setAttribute("email",email);
				response.sendRedirect("UserHome.jsp");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
