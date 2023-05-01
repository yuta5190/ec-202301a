package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.SortType;
import com.example.repository.ItemRepository;

/**
 * 商品情報を操作するサービス.
 * 
 * @author yoshida_yuta
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
		List<Item> itemList = (name.isBlank()) ? repository.findAll() : repository.findByName(name);
		return itemList;
	}

	/**
	 * 並び替え
	 * 
	 * 新しく要素を入れる時はHTMLのOPTIONに追加、domainのSortTypeに追加
	 * 
	 * @param sort HTMLでプルダウンｎvalue
	 * @return 並び順を変えたitemList
	 */
	public List<Item> sortItem(Integer sort) {
		String sortType = SortType.getSortTypeByNum(sort).getName();
		List<Item> sortList = repository.sort(sortType);
		return sortList;
	}

}
