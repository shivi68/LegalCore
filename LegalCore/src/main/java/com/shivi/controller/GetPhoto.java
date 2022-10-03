package com.shivi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shivi.model.DAO;


@WebServlet("/GetPhoto")
public class GetPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String type=request.getParameter("type");
			DAO d=new DAO();
			byte b[]=d.getPhoto(email, type);
			response.getOutputStream().write(b);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
