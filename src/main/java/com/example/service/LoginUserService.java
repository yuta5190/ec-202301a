package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * Userリポジトリを操作するサービスクラス.
 * 
 * @author matsuokatoshiichi
 *
 */
@Service
@Transactional
public class LoginUserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ログインをする為にメールアドレスとパスワードをチェックする.
	 * 
	 * @param email 入力されたメールアドレス
	 * @param password　入力されたパスワード
	 * @return
	 */
	public User login(String email,String password) {
		
		User user = userRepository.findByMailAddressAndPassword(email, password);
		
		return user;
		
	}
	
	
}
