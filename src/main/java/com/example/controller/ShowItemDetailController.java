package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemDetailService;

/**
 * 商品詳細を操作するコントローラ.
 * 
 * @author ichiyoshikenta
 *
 */
@Controller
@RequestMapping("/item/showItemList")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService service;

	@GetMapping("/showItemDetail")
	public String showItemDetail(Integer itemId, Model model) {
		Item item = service.showItemDetail(itemId);
		model.addAttribute("item", item);
		return "item_detail";
	}
}
