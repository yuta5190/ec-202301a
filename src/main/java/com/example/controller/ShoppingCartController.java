package com.example.controller;

import java.util.Optional;

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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

/**
 * ショッピングカード情報を操作するコントローラークラス.
 * 
 * @author 　yoshida_yuta
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
	public String insert(ShoppingCartForm shoppingCartForm, Model model, @AuthenticationPrincipal LoginUser loginUser,
			HttpServletRequest request) {
		// 変更箇所 else内だけにすれば元に戻る
		Integer userId = null;
		if (loginUser == null) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				userId = cookie.getValue().hashCode();
			}
			shoppingCartService.insert(shoppingCartForm, userId);

		} else {
			userId = loginUser.getUser().getId();
			shoppingCartService.insert(shoppingCartForm, userId);
		}
		System.out.println(userId);
		// 変更箇所ここまで

		return "redirect:/shoppingcart/to-cartlist";
	}

	/**
	 * カート追加時のリダイレクト先. ログイン状態のユーザーの注文情報をカートリストに表示
	 * 
	 * @param model     リクエストスコープ
	 * @param loginUser ログイン中のユーザー情報
	 * @return
	 */
	@GetMapping("/to-cartlist")
	public String toCartList(Model model, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest request) {
	
		Integer userId = 0;
		if (loginUser == null) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				userId = cookie.getValue().hashCode();
			}
		} else {
			userId = loginUser.getUser().getId();
		}

		Optional<Order> optOrder = shoppingCartService.load(userId);

		if (optOrder.isPresent()) {
			model.addAttribute("order", optOrder.get());
			
		}else {
			Order order=new Order();
			String emptyMessage = "カートに商品がありません";
			model.addAttribute("order", order);
			model.addAttribute("emptyMessage", emptyMessage);
		}
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
