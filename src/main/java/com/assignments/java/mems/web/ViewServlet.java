package com.assignments.java.mems.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignments.java.mems.bo.UserBO;
import com.assignments.java.mems.dao.UserDAOImpl;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/ViewServlet", "/View" })
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException 
	{

		System.out.println("ViewSerlvet - doGet() - invoked");
		
		UserBO userBO = null;
		
		//1. Get the UserBO from Session if it exists
		userBO = (UserBO) request.getSession().getAttribute("userBO");
		
		System.out.println("UserBO from Session : " + userBO);
		
		if(null!=userBO) {
			request.setAttribute("userBO", userBO);
			System.out.println("Redirecting to profile/view.jsp with the userBO object");
			request.getRequestDispatcher("profile/view.jsp").forward(request, response);
			return;
		}
		
		System.out.println("UserBO is not present in the Session. Attemping to look for an Id param in Query String");
		
		//2. Get the Id parameter from the Query String (in URL) if exists
		String idParam = request.getParameter("id");
		int id = -1;
		
		if(!Objects.isNull(idParam)) {
			id = Integer.parseInt(String.valueOf(idParam));
		} 
		
		System.out.println("Id :: " + id);
		
		if(id==-1) { /* No valid Id was passed in URL */
			request.setAttribute("errorMsg", "Missing Id Attribute");
			System.out.println("Redirecting to the Index page with an error Message");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		int sqlErrorCode = -1;
		String sqlState = null;
		
		System.out.println("Attempting to load the user details from Database");
		
		/* Get the User details from Database, if the id is valid */
		try {
			userBO = new UserDAOImpl().getUserById(id);
		}catch(Exception exception) {
			System.err.println("Exception while getting an User By Id.");
			System.err.println("Error Message : " + exception.getMessage());
			if(exception instanceof SQLException) {
				sqlErrorCode = ((SQLException) exception).getErrorCode();
				sqlState = ((SQLException) exception).getSQLState();
				System.err.println("SQL Error Code : " + sqlErrorCode);
				System.err.println("SQL State : " + sqlState);
			}
		}
		
		/* Finally validate the UserBO and prepare the error message */
		if(null==userBO) { /* No User exists */
			request.setAttribute("errorMsg", "No User exists for the Id - " + id);
			System.out.println("Redirecting to profile/view.jsp with the error Message");
		} else {
			request.setAttribute("userBO", userBO);
			System.out.println("Redirecting to profile/view.jsp with the userBO object");
		}
		
		request.getRequestDispatcher("profile/view.jsp").forward(request, response);
	}

}
