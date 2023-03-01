package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	public String index() {
		return "item_detail";
	}

	/**
	 * 商品詳細画面より入力された値をDBに挿入する.
	 * 
	 * @param shoppingCartForm 商品詳細画面より入力された値
	 * @param orderId          注文ID
	 */
	@PostMapping("/add-cart")
	public String insert(ShoppingCartForm shoppingCartForm, Model model, Integer orderId) {
		Integer userId = (Integer) session.getAttribute("id");
		shoppingCartService.insert(shoppingCartForm, userId, orderId);
		return "redirect:/shoppingcart/to-cartlist";
	}

	@GetMapping("/to-cartlist")
	public String toCartList() {
		return "cart_List";
	}
}
