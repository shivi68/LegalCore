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


@WebServlet("/AddAdvice")
public class AddAdvice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String a_email=request.getParameter("a_email");
			String u_email=request.getParameter("u_email");
			String a_name=request.getParameter("a_name");
			String query=request.getParameter("query");
			DAO d=new DAO();
			String result=d.addAdvice(a_email,u_email,query);
			if(result.equalsIgnoreCase("success")) {
				HttpSession session=request.getSession();
				session.setAttribute("msg","Your Query Sent Successfully!");
				response.sendRedirect("GetAdvice.jsp?a_email="+a_email+"&a_name="+a_name);
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("msg","Your Query Sent Failed!");
				response.sendRedirect("GetAdvice.jsp?a_email="+a_email+"&a_name="+a_name);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}