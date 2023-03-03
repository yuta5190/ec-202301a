package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import com.example.service.ShowItemListService;

/**
 * 商品情報を操作するコントローラ.
 * 
 * @author ichiyoshikenta
 *
 */
@Controller
@RequestMapping("/")
public class ShowItemListController {
	@Autowired
	ShowItemListService service;
	@Autowired
	ItemRepository repository;


	/**
	 * 商品一覧を出力
	 * 
	 * @param model モデル
	 * @param name  検索する商品名
	 * @return 商品一覧画面
	 */
	@GetMapping("")
	public String showItemList(Model model, String name, Integer page) {

		List<Item> itemList = service.showItemList(name);
		if (itemList.size() == 0) {
			model.addAttribute("errorMessage", "該当する商品はありません。");
			itemList = service.showItemList(null);
		}
		 model.addAttribute("itemList", itemList);

		return "item_list";
	}


}
