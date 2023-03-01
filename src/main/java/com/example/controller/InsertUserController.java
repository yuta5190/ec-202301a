package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String index(InsertUserForm form, Model model) {

		return "register_admin";
	}

	/**
	 * 入力された情報を受け取りユーザー登録をします.
	 * 
	 * @param form   入力されたユーザー情報
	 * @param mlodel リクエストスコープを使う為の変数
	 * @return ログイン画面にリダイレクトします
	 */
	@PostMapping("/insert")
	public String insert(@Validated InsertUserForm form, BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		
		if(!(form.getEmail().equals(form.getConfirmationPassword()))) {
			FieldError fieldError = new FieldError(result.getObjectName(), "confirmationPassword", "パスワードと確認用パスワードが不一致です");
			result.addError(fieldError);
		}
		
		User user = new User();
		user = insertUserService.findByMaillAddress(form.getEmail());

		if (!(user == null)) {
			FieldError fieldError = new FieldError(result.getObjectName(), "email", "そのメールアドレスは既に使われています");
			result.addError(fieldError);
		}

		if (result.hasErrors()) {
			return index(form, model);
		}

		user.setName(form.getLastName() + form.getFirstName());
		BeanUtils.copyProperties(form, user);
		System.out.println(user);
		insertUserService.insert(user);

		return "redirect:login";
	}

}
