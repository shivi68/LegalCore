package com.shivi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.shivi.model.DAO;

@WebServlet("/SearchAdvocate")
public class SearchAdvocate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String category=request.getParameter("category");
			DAO d=new DAO();
			ArrayList<HashMap> advocates=d.getAdvocatesByCategory(category);
			request.setAttribute("advocates", advocates);
			request.setAttribute("category", category);
			RequestDispatcher rd=request.getRequestDispatcher("AdvocatesSearch.jsp");
			rd.forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
