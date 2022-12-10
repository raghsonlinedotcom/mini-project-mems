/**
 * 
 */
package test.assignments.java.mems.dao;

import com.assignments.java.mems.bo.UserBO;
import com.assignments.java.mems.dao.UserDAO;
import com.assignments.java.mems.dao.UserDAOImpl;

/**
 * @author raghavan.muthu
 *
 */
public class UserDAOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//testRegisterUser();
		//testGetUserById();
		updateUser();
	}
	
	private static void updateUser() {
		UserBO userBO = getUserBO(1);
		UserDAO userDAO = new UserDAOImpl();
	
		
		userBO.setFirstName("Arun-2");
		int rowsUpdated = 0;
		
		try {
			rowsUpdated = userDAO.update(userBO);
		} catch (Exception exception) {
			System.err.println("Exception while updating an User");
			System.err.println("Error Message : " + exception.getMessage());
			//TODO ONLY for Development Purposes, remove it in PROD
			exception.printStackTrace();
		}
		
		System.out.println("rowsUpdated : " + rowsUpdated);
		
		System.out.println("------- VERIFYING from Database ---");
		getUserBO(1);
	}

	private static void testGetUserById() 
	{
		getUserBO(1);		
	}
	
	public static UserBO getUserBO(int id)
	{
		UserBO userBO = null;
		
		try {
			userBO = new UserDAOImpl().getUserById(id);
		} catch (Exception exception) {
			System.err.println("Exception while retrieving an User By Id.");
			System.err.println("Error Message : " + exception.getMessage());
			//TODO ONLY for Development Purposes, remove it in PROD
			exception.printStackTrace();
		}
		
		System.out.println("UserBO :: "+ userBO);
		
		return userBO;
	}

	public static void testRegisterUser() 
	{
		UserBO userBO = new UserBO(999, "Test2", "Test2 UserDAO", "Test2", "test@2");
		UserDAO userDAO = new UserDAOImpl();
		
		try {
			userDAO.registerUser(userBO);
		} catch (Exception exception) {
			System.err.println("Exception while registering an User.");
			System.err.println("Error Message : " + exception.getMessage());
			//TODO ONLY for Development Purposes, remove it in PROD
			exception.printStackTrace();
		}
	}

}
