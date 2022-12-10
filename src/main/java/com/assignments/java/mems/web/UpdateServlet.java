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
import com.assignments.java.mems.dao.UserDAOImpl;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet(
		description = "A Servlet to update the User Profile", 
		urlPatterns = { 
				"/UpdateServlet", 
				"/Update"
		})
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException 
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("UpdateServlet - doPost() invoked!");
		
		//1. Collect the input data
		String id = String.valueOf(request.getParameter("id"));
		String firstName = String.valueOf(request.getParameter("firstName"));
		String lastName = String.valueOf(request.getParameter("lastName"));
		String userName = String.valueOf(request.getParameter("userName"));
		//String password = String.valueOf(request.getParameter("password"));
		
		boolean isValidationError = false;
		String errorMsg = "<ul>";
		
		//2. Prepare the UserBO object
		UserBO userBO = new UserBO(Integer.parseInt(id), firstName, 
						lastName, userName, null);
		
		/* 3. Validate the parameters */
		if(null==firstName || firstName.trim().length()>20) {
			errorMsg = appendErrorMsgForWeb(errorMsg, "First Name can contain a max of 20 chars");
			request.setAttribute("firstName", firstName);
			isValidationError = true;
		}
		
		if(null==lastName || lastName.trim().length()>20) {
			errorMsg = appendErrorMsgForWeb(errorMsg, "Last Name can contain a max of 20 chars");
			request.setAttribute("lastName", lastName); 
			isValidationError = true;
		}
		
		if(null==userName || userName.trim().length()>20) {
			errorMsg = appendErrorMsgForWeb(errorMsg, "User Name can contain a max of 20 chars");			
			request.setAttribute("userName", userName);
			isValidationError = true;
		}
		
		if(isValidationError) {
			errorMsg = errorMsg + "</ul>";
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("userBO", userBO);
			request.getRequestDispatcher("profile/edit.jsp").forward(request, response);
			return;
		}
		
		//4. Validate the userName - to be unique
		// can be handled in the exception by verifying 
		// the Exception class Type
		
		
		//5. Update the User details in Database
		int rowsUpdated = 0;
		Exception exceptionObj = null;
		
		try {
			rowsUpdated = new UserDAOImpl().update(userBO);
		} catch (Exception exception) {
			System.err.println("Exception while updating an User");
			System.err.println("Error Message : " + exception.getMessage());
			//TODO ONLY for Development Purposes, remove it in PROD
			exception.printStackTrace();
			exceptionObj = exception;
		}
		
		System.out.println("rowsUpdated : " + rowsUpdated);
		
		if(rowsUpdated<=0) { /* No update happened */
			request.setAttribute("errorMsg", "User Details were NOT updated!");
			if(exceptionObj instanceof SQLIntegrityConstraintViolationException) {
				request.setAttribute("errorMsg", "UserName is already chosen by a different user.");
				System.err.println("SQL Error Code : " + ((SQLException) exceptionObj).getErrorCode());
				System.err.println("SQL State : " + ((SQLException) exceptionObj).getSQLState());
			}
			request.setAttribute("userBO", userBO);
			request.getRequestDispatcher("profile/edit.jsp").forward(request, response);
		} else {
			request.setAttribute("userBO", userBO);
			request.setAttribute("message", "User Details updated successfully!");
			request.getRequestDispatcher("profile/view.jsp").forward(request, response);
		}
	}
	
	private String appendErrorMsgForWeb(String prevError, String newError)
	{
		return prevError  + "<li>" + newError + "</li>";
	}

}
