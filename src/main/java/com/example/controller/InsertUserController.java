package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.InsertUserForm;
import com.example.service.InsertUserService;

/**
 * ユーザー登録を行うコントローラー.
 * 
 * @author matsuokatoshiichi
 *
 */
@Controller
@RequestMapping("/insert-user")
public class InsertUserController {

	@Autowired
	private InsertUserService insertUserService;
	
	@GetMapping("")
	public String index(InsertUserForm form,Model model) {
		
		return "register_admin";
	}
	
	/**
	 * 入力された情報を受け取りユーザー登録をします.
	 * 
	 * @param form　入力されたユーザー情報
	 * @param mlodel　リクエストスコープを使う為の変数
	 * @return　ログイン画面にリダイレクトします
	 */
	@PostMapping("/insert")
	public String insert(InsertUserForm form,Model mlodel) {
		User user = new User();
		
		BeanUtils.copyProperties(form, user);
		
		user.setName(form.getLastName()+form.getFirstName());
		
		System.out.println(user);
		insertUserService.insert(user);
		
		return "redirect:login";
	}
	
	
}
