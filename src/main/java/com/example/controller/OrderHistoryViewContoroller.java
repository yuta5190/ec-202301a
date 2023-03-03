package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;

@Controller
@RequestMapping("/orederhistory")
public class OrderHistoryViewContoroller {
@GetMapping("/view")
public String view(Order order) {
return"order_history";
}
}
