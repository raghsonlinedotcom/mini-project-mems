package com.assignments.java.mems.web;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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
		
		/* Validate the parameters */
		if(null==firstName || firstName.trim().length()>20) {
			request.setAttribute("errorMsg", "First Name can contain a max of 20 chars");
			request.setAttribute("firstName", firstName);
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		//2. Prepare the BO object
		UserBO userBO = new UserBO(firstName, lastName, userName, password);
		System.out.println("UserBO : " + userBO);
		
		//3. Save it into the Database
		UserDAO userDAO = new UserDAOImpl();
		int lastInsertedId = -1;
		String errorMsg = null;
		Exception exceptionObj = null;
		/* MEMS-18, MEMS-19 */
		int sqlErrorCode = -1;
		String sqlState = null;
		
		boolean isError = false;
		
		try {
			lastInsertedId = userDAO.registerUser(userBO);
		} catch(ClassNotFoundException classNotFoundException) { /* MEMS-18, MEMS-19 */
			isError = true;
			exceptionObj = classNotFoundException;				
		} catch (SQLException sqlException) {  /* MEMS-18, MEMS-19 */
			isError = true;
			exceptionObj = sqlException;
			sqlErrorCode = sqlException.getErrorCode();
			sqlState = sqlException.getSQLState();
		} catch (Exception exception) {
			isError = true;			
			exceptionObj = exception;			
		}
		
		/* Handling the error - at once in common for all the different types */
		/* MEMS-18, MEMS-19 */
		if(isError) 
		{
			System.err.println("Exception while registering the User");
			errorMsg = exceptionObj.getMessage();
			System.err.println("Error Message : " + errorMsg);	
			if(exceptionObj instanceof SQLException) {
				System.err.println("Error Code : " + sqlErrorCode);
				System.err.println("SQL State : " + sqlState);
			}
			//TODO ONLY for Development Purposes, remove it in PROD
			exceptionObj.printStackTrace();
		}
		
		//------ MEMS-14 - BugFix - START ----
		String message = null;
		
		if(lastInsertedId<=0) { /* Error */
			message = "Error while registering the User. "; 
			
			if(exceptionObj instanceof ClassNotFoundException) {
				message = "Error connecting with the Database. Please contact Admin.";
			} else if(exceptionObj instanceof SQLIntegrityConstraintViolationException ) {
				message = message + "User already exists";
			} else {
				message = message + " Reason : " + errorMsg;
			}			
		} else { /* Success */ 
			message = "Registration successful. Your User Id : " + lastInsertedId;
		}
		
		System.out.println("Last Inserted Id : " +lastInsertedId);
		
		response.getWriter().println(message);
		//----- MEMS-14 - BugFix - END ------
	}

}
