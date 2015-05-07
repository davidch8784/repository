package com.samsung.biz.users;

import com.samsung.biz.users.impl.UserDAO;
import com.samsung.biz.users.vo.UserVO;

public class LoginTest {
	public static void main(String[] args) {
		
		UserVO vo = new UserVO();
		vo.setId("abc");
		vo.setPassword("abc123");
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.login(vo);
		
		System.out.println(user);
	}
}
