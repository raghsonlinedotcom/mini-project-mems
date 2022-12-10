/**
 * 
 */
package com.assignments.java.mems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.assignments.java.mems.bo.UserBO;
import com.assignments.java.mems.db.DBConnection;

/**
 * @author raghavan.muthu
 *
 */
public class UserDAOImpl implements UserDAO {

	@Override
	public int registerUser(UserBO userBO) 
	throws Exception
	{
		// 1. Obtain the DB Connection
		Connection conn = DBConnection.getConn();
		
		//2. Prepare the SQL Query and Statement Object
		String sql = "INSERT INTO User (`FirstName`, " 
				+ "`LastName`, `UserName`, `Password`) " 
				+ "VALUES (?, ?, ?, ?)";
		
		PreparedStatement ps = null;
		int rowsAdded = 0;
		int lastInsertedId = 0;
		ResultSet rs = null;
		
		ps = conn.prepareStatement(sql);
		
		//3. Set/ Bind Values to the Prepared Statement
		ps.setString(1, userBO.getFirstName());
		ps.setString(2, userBO.getLastName());
		ps.setString(3, userBO.getUserName());
		ps.setString(4, userBO.getPassword());
		
		//4. Execute the Statement
		rowsAdded = ps.executeUpdate();
		
		rs = ps.executeQuery("SELECT LAST_INSERT_ID()");

	    if (rs.next()) {
	    	lastInsertedId = rs.getInt(1);
	    } else {
	        System.err.println("There was no record inserted in this session!");
	    }
	    
	    /* Close the DB Resources */
	    rs.close();
	    ps.close();
		
		System.out.println("Rows Added : "+ rowsAdded);
		System.out.println("Last Inserted Id : "+ lastInsertedId);
		
		return lastInsertedId;
	}

	@Override
	public UserBO getUserById(int id) 
		throws Exception 
	{
		// 1. Obtain the DB Connection
		Connection conn = DBConnection.getConn();
		
		UserBO userBO = null;
		
		//2. Prepare the SQL Query and Statement Object
		String sql = "SELECT * FROM USER WHERE ID = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		//3. Set/ Bind Values to the Prepared Statement
		ps.setInt(1, id);
		
		//4. Execute Query
		ResultSet rs = ps.executeQuery();
		
		//5. Process the Result
		while(rs.next()) 
		{
			String firstName = rs.getString("FirstName");
			String lastName = rs.getString("LastName");
			String userName = rs.getString("UserName");
			String password = rs.getString("Password");
			boolean isActive = rs.getBoolean("IsActive");
			
			userBO = new UserBO(id, firstName, lastName, userName, password);
			userBO.setActive(isActive);
		}
		
		System.out.println("UserBO retrieved from DB : " + userBO);
		
		return userBO;
	}
	
	@Override
	public int update(UserBO userBO) 
		throws Exception 
	{
		// 1. Obtain the DB Connection
		Connection conn = DBConnection.getConn();
		
		//2. Prepare the SQL Query and Statement Object
		String sql = "UPDATE USER SET FirstName=?, LastName=?, " + 
					"UserName=? WHERE ID=?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		//3. Set/ Bind Values to the Prepared Statement
		ps.setString(1, userBO.getFirstName());
		ps.setString(2, userBO.getLastName());
		ps.setString(3, userBO.getUserName());
		ps.setInt(4, userBO.getId());
		
		//4. Execute Query
		int rowsUpdated = ps.executeUpdate();
		
		System.out.println("rowsUpdated : " + rowsUpdated);
		
		return rowsUpdated;
	}

}
