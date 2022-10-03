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


@WebServlet("/ReplyAdvice")
public class ReplyAdvice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			String reply=request.getParameter("reply");
			DAO d=new DAO();
			d.replyAdvice(id,reply);
			response.sendRedirect("AdvocateHome.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
