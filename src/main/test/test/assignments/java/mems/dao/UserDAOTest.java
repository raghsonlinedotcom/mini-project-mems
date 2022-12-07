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
	public static void main(String[] args) {
		testRegisterUser();
	}
	
	public static void testRegisterUser()
	{
		UserBO userBO = new UserBO(999, "Test2", "Test2 UserDAO", "Test2", "test@2");
		UserDAO userDAO = new UserDAOImpl();
		
		userDAO.registerUser(userBO);
	}

}
