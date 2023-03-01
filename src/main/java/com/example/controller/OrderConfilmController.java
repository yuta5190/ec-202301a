package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
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
	@PostMapping("/orderpost")
	public String orderPost(Integer userId, Model model) {
		if (userId == null) {
			return "login";
		} else {
			Order order = orderconfilmservice.findByOrderid(userId);
			model.addAttribute("order", order);
			return "order_confilm";
		}
	}

}
