package com.assignments.java.mems.dao;

import com.assignments.java.mems.bo.UserBO;

public interface UserDAO {
	
	public abstract int registerUser(UserBO userBO) throws Exception;
	
	public abstract UserBO getUserById(int id) throws Exception;
	
	public abstract int update(UserBO userBO) throws Exception;

}
