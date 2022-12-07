package com.assignments.java.mems.bo;

public class UserBO {
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String password;
	
	private boolean isActive = true;
	
	/* ====== Constructors  ===== */
	
	/**
	 * <p>
	 * 	An all-arg constructor used to instantiate when 
	 * the instance values are obtained from the Database,
	 * which will have the ID (PK) value.
	 * </p>
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 */
	public UserBO(int id, String firstName, String lastName, String userName, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	

	/**
	 * <p>
	 * 	An all-but-one arg constructor without the PK/ID, which 
	 * is used for the other occurrences including the instance
	 * creation from a Web Form, Unit Testing etc.,
	 * </p>
	 *
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 */
	public UserBO(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}


	/* ====== Getters and Setters  ===== */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}


	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	@Override
	public String toString() {
		return "UserBO [" 
				+ "hashCode()=" + hashCode() 
				+ ", id=" + id 
				+ ", firstName=" + firstName 
				+ "\n, lastName=" + lastName 
				+ ", userName=" + userName
				+ ", password=" + password 
				+ ", isActive=" + isActive
				+ "]";
	}


		
}
