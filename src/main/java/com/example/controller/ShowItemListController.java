package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemListService;

/**
 * 商品情報を操作するコントローラ.
 * 
 * @author ichiyoshikenta
 *
 */
@Controller
@RequestMapping("/item")
public class ShowItemListController {
	@Autowired ShowItemListService service;
	
	@GetMapping("/showItemList")
	public String showItemList(Model model,String name) {
		List<Item>itemList = service.showItemList();
		model.addAttribute("itemList",itemList);
		return "item_list";
	}
	
}
