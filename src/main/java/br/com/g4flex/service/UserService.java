package br.com.g4flex.service;

import br.com.g4flex.dao.UserDao;
import br.com.g4flex.entity.User;

public class UserService {
	
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public User login(String email, String senha) {
		return userDao.find(email,senha);
	}
	
	public boolean logout() {
		return false;
	}
	
	public void create(User user) { 
		try {
			userDao.save(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
