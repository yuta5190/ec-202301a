package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.OrderForm;
import com.example.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
@Autowired
private OrderService orderservice;


@PostMapping("/orderinfosend")
public String orderInfoSend(OrderForm orderform) {
	orderservice.updateOrder(orderform);
	return "order_finished";
}

}
