package test.assignments.java.mems.bo;

import com.assignments.java.mems.bo.UserBO;

public class UserBOTest {

	public static void main(String[] args) {
		UserBO userBO = new UserBO(999, "Test", "Test UserDAO", "Test1", "test@1");
		System.out.println("UserBO : "+ userBO);
	}

}
