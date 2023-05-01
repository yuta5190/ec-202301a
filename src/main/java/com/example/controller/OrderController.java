package com.example.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.UserInfo;
import com.example.form.OrderForm;
import com.example.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 注文時のコントローラークラス
 * 
 * @author yoshida_yuta
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderservice;
	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * 注文情報をDBに保存するメソッド
	 *
	 * @param orderform 注文時入力フォーム
	 * @param result    エラーメッセージをhtmlに返す因数
	 * @param model     モデル
	 * @param loginUser ログインしているユーザーの情報
	 * @param request   クッキー情報の取得
	 * @return 決済後画面
	 */
	@PostMapping("/orderinfosend")
	public String orderInfoSend(@Validated OrderForm orderform, BindingResult result, Model model,
			@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest request) {
		if (orderform.getOrderDate().equals("")) {
			return "redirect:/vieworder?model=" + model +  "&orderform=" + orderform +  "&loginUser=" + loginUser;
		}
		
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dates = LocalDate.parse(orderform.getOrderDate(), formatter);
		LocalDateTime datetime = dates.atStartOfDay();
		datetime=datetime.plusHours(Integer.parseInt(orderform.getOrderTime())-3);
		if (datetime.isBefore(date)) {
			FieldError fieldError = new FieldError(result.getObjectName(), "orderDate", "今から3時間後の日時をご入力ください");
			result.addError(fieldError);
				}
		
		if (result.hasErrors()) {
			return "redirect:/vieworder?model=" + model +  "&orderform=" + orderform +  "&loginUser=" + loginUser;
		}
		;
		UserInfo user = loginUser.getUser();
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("yuta@sample.com");
		mailMessage.setReplyTo("yuta@sample.com");
		mailMessage.setFrom("yuta@sample.com");
		mailMessage.setSubject("テストメール");
		mailMessage.setText("テストメールです");
		javaMailSender.send(mailMessage);
		orderservice.updateOrder(orderform, user);
		return "order_finished";
	}
}