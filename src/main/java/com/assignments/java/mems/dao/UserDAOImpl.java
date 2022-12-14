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
		
		System.out.println("Rows Added : "+ rowsAdded);
		System.out.println("Last Inserted Id : "+ lastInsertedId);
		
		return lastInsertedId;
	}

}
