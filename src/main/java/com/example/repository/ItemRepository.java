package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * itemsテーブルを操作するリポジトリ.
 * 
 * @author ichiyoshikenta
 *
 */
@Repository
public class ItemRepository {
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath("image_path");
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};

	@Autowired
	NamedParameterJdbcTemplate template;

	/**
	 * 商品一覧を表示する.
	 * 
	 * @return 全商品一覧
	 */
	public List<Item> findAll() {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items;";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	
}
