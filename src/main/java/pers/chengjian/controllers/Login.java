package pers.chengjian.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.chengjian.utils.DifferentRandom;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String background = request.getParameter("background");
		HttpSession session = request.getSession();
		session.setAttribute("user_name", user_name);
		session.setAttribute("sex", sex);
		session.setAttribute("age", age);
		session.setAttribute("background", background);
		session.setAttribute("index", 1);
		session.setAttribute("image_id", DifferentRandom.randintList(20, 60));
		session.setAttribute("result", new ArrayList<HashMap<String, Integer> >());
		response.sendRedirect("main/survey.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
