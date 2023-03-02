package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

/**
 * 商品詳細を操作するサービス
 * 
 * @author ichiyoshikenta
 *
 */
@Service
@Transactional
public class ShowItemDetailService {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;

	public Item showItemDetail(Integer itemId) {
		Item item = itemRepository.load(itemId);
		item.setToppingList(toppingRepository.findAll());
		return item;
	}
}
