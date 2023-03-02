package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.OrderConfilmService;

/**
 * 注文内容を表示するコントローラー
 * 
 * @author yoshidayuuta
 *
 */
@Controller
@RequestMapping("/orderconfilm")
public class OrderConfilmController {

	@Autowired
	private OrderConfilmService orderconfilmservice;

	/**
	 * ユーザーIDからカートの商品情報を受け取るメソッド
	 * 
	 * @param userId ログインユーザーID
	 * @return 注文内容確認画面
	 */
	
	@GetMapping("")
	public String index() {
		return"cart_list";
	}
	/**
	 * ログインしているユーザー情報を受け取り、詳細確認画面を表示させる。
	 * @param userId　　ユーザーID
	 * @param model モデル
	 * @return　詳細確認画面
	 */
	@PostMapping("/vieworder")
	public String orderPost(Integer userId, Model model,OrderForm orderform) {
		System.out.println(userId);
		if (userId == null) {
			return "login";
		} else {
			Order order = orderconfilmservice.findByOrderid(userId);
			System.out.println("a");
			System.out.println(order);
			model.addAttribute("order", order);
			return "order_confirm";
		}
	}

}
