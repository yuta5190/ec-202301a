package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.ShoppingCartForm;
import com.example.service.ShoppingCartService;

/**
 * ショッピングカード情報を操作するコントローラークラス.
 * 
 * @author seiji_kitahara
 *
 */
@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * 商品詳細画面より入力された値をDBに挿入する. ダブルサブミット対策として、リダイレクト
	 * 
	 * @param shoppingCartForm 商品詳細画面より入力された値
	 * @param orderId          注文ID
	 */
	@PostMapping("/cart")
	public String insert(ShoppingCartForm shoppingCartForm, Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = loginUser.getUser().getId();
		System.out.println(userId);

		shoppingCartService.insert(shoppingCartForm, userId);

		return "redirect:/shoppingcart/to-cartlist";
	}

	/**
	 * カート追加時のリダイレクト先. 
	 * ログイン状態のユーザーの注文情報をカートリストに表示
	 * 
	 * @param model     リクエストスコープ
	 * @param loginUser ログイン中のユーザー情報
	 * @return
	 */
	@GetMapping("/to-cartlist")
	public String toCartList(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Order order = new Order();
		Integer userId = loginUser.getUser().getId();
		order = shoppingCartService.load(userId);
		
		if(order.getOrderItemList().size() == 0) {
			String emptyMessage = "カートに商品がありません";
			model.addAttribute("emptyMessage",emptyMessage);
		}
		
		System.out.println(order);
		model.addAttribute("order", order);
		return "cart_List";
	}

	/**
	 * カート内の選択された商品を削除する.
	 * 
	 * @param 選択された削除対象となる商品のID
	 * @return カートリストにリダイレクト
	 */
	@PostMapping("/delete")
	public String delete(Integer orderItemId) {
		shoppingCartService.deleteByOrderItemId(orderItemId);
		return "redirect:/shoppingcart/to-cartlist";
	}

}
