package com.assignments.java.mems.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignments.java.mems.bo.UserBO;
import com.assignments.java.mems.dao.UserDAO;
import com.assignments.java.mems.dao.UserDAOImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet({ "/RegisterServlet", "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("RegisterServlet - doPost() invoked");
		
		//response.setContentType("text/html");
		//response.getWriter().println("RegisterServlet invoked!");
		
		//1. Collect the input data
		String firstName = String.valueOf(request.getParameter("firstName"));
		String lastName = String.valueOf(request.getParameter("lastName"));
		String userName = String.valueOf(request.getParameter("userName"));
		String password = String.valueOf(request.getParameter("password"));
		
		//2. Prepare the BO object
		UserBO userBO = new UserBO(firstName, lastName, userName, password);
		System.out.println("UserBO : " + userBO);
		
		//3. Save it into the Database
		UserDAO userDAO = new UserDAOImpl();
		int lastInsertedId = userDAO.registerUser(userBO);
		System.out.println("Last Inserted Id : " +lastInsertedId);
		
		response.getWriter().println("Registration successful. Your User Id : " + lastInsertedId);
		
	}

}
