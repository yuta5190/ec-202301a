package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.ShoppingCartForm;
import com.example.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

	@Autowired
	private HttpSession session;

	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * 商品詳細画面より入力された値をDBに挿入する.
	 * 
	 * @param shoppingCartForm 商品詳細画面より入力された値
	 * @param orderId          注文ID
	 */
	@PostMapping("/cart")
	public String insert(ShoppingCartForm shoppingCartForm, Model model) {
		System.out.println(shoppingCartForm);
		System.out.println("セッションID確認");
//		System.out.println(session.getAttribute("user"));
//		User user = (User)session.getAttribute("user");
//		Integer userId = user.getId();
//		System.out.println(userId);
		// TODO ↓確認用、あとで消す
		Integer userId = 2;
		
		shoppingCartService.insert(shoppingCartForm, userId);
		
		return "redirect:/shoppingcart/to-cartlist";
//		return "cart_List";
	}

	@GetMapping("/to-cartlist")
	public String toCartList(Model model) {
		Order order = new Order();
		order = shoppingCartService.load(2);
		System.out.println("カート内確認");
		System.out.println(order);
		model.addAttribute("order", order);
		return "cart_List";
	}
	
	@PostMapping("/delete")
	public String delete(Integer orderItemId) {
		shoppingCartService.deleteByOrderItemId(orderItemId);
		return "redirect:/shoppingcart/to-cartlist";
	}
	
}
