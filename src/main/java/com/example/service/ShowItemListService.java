package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品情報を操作するサービス.
 * 
 * @author ichiyoshikenta
 *
 */

@Service
@Transactional
public class ShowItemListService {
	@Autowired
	private ItemRepository repository;

	/**
	 * 商品情報を全件取得する.
	 * 
	 * @param name 商品名
	 * @return 商品一覧.
	 */
	public List<Item> showItemList(String name) {
		List<Item> itemList = repository.findAll();

		if (name == null) {
			itemList = repository.findAll();
		} else {
			itemList = repository.findByName(name);
		}
		return itemList;
	}
	


}
