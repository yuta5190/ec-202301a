package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.service.LoginUserService;

import jakarta.servlet.http.HttpSession;

/**
 * ログイン/ログアウトをするコントローラー.
 * 
 * @author matsuokatoshiichi
 *
 */

@Controller
@RequestMapping("/login-user")
public class LoginUserController {

	@Autowired
	private LoginUserService loginUserService;
	
	@Autowired
	private HttpSession session;

	/**
	 * ログイン画面を表示させます.
	 * 
	 * @param form  入力されたログイン情報
	 * @param model ログイン情報をリクエストスコープに格納する変数
	 * @return
	 */
	@GetMapping("")
	public String index(LoginUserForm form, Model model) {

		return "login";

	}

	/**
	 * 入力された情報を受け取りログインをします.
	 * 
	 * @param form　入力されたログイン情報
	 * @param model　リクエストスコープを格納する為の変数
	 * @return 商品一覧画面へリダイレクトします
	 */
	@PostMapping("/login")
	public String login(LoginUserForm form, Model model,RedirectAttributes redirectAttributes) {

		User user = loginUserService.login(form.getEmail(), form.getPassword());
		
		
		if (user == null) {
			redirectAttributes.addFlashAttribute("errorMessage","メールアドレス、またはパスワードが間違っています");
			return "redirect:/login-user/";
		}
		

		return "item_list_toy";

	}

	/**
	 * ログアウトします.
	 * 
	 * @return ログイン画面にリダイレクトします
	 */
	@GetMapping("/logout")
	public String logout() {
		
		session.invalidate();
		
		return "redirect:/login-user/";

	}

}
