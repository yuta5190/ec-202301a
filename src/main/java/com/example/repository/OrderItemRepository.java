package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.form.ShoppingCartForm;

public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	


	/**
	 * 商品詳細画面より入力された値をDBに挿入する.
	 * 
	 * @param shoppingCartForm 商品詳細画面より入力された値
	 * @param orderId 注文ID
	 */
	public void insert(ShoppingCartForm shoppingCartForm, Integer orderId) {
		StringBuilder insertsql = new StringBuilder();
		insertsql.append("INSERT INTO order_items(item_id, order_id, quantity, size)");
		insertsql.append(" VALUES(:itemId, :quantity, :size);");
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", shoppingCartForm.getItemId())
				.addValue("order_id", orderId).addValue("quantity", shoppingCartForm.getQuantity())
				.addValue("size", shoppingCartForm.getSize());
		template.update(insertsql.toString(), param);
	}
	
//	public OrderItem load(Integer id) {
//		String sql = "SELECT id, item_id, order_id, quantity, size FROM order_items WHERE id = :id;";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
//		OrderItem orderItem = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
//		return orderItem;
//	}
}
