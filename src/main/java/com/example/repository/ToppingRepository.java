package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Topping;

/**
 * toppingsテーブルを操作するリポジトリ.
 * 
 * @author yoshida_yuta
 *
 */
@Repository
public class ToppingRepository {

	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		return topping;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * トッピングを全件取得する.
	 * 
	 * @return 検索されたトッピング情報
	 */
	public List<Topping> findAll() {

		String sql = "SELECT id,name,price_m,price_l FROM toppings  ORDER BY price_m;";

		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);

		return toppingList;
	}
	
	public List<Topping> load(List<Integer> toppingIdList) {
		System.out.println(toppingIdList);
		String sql = "SELECT id,name,price_m,price_l FROM toppings WHERE id = :toppingId";
		List<Topping> toppingList = new ArrayList<>();
		for(Integer toppingId : toppingIdList) {
			SqlParameterSource param = new MapSqlParameterSource().addValue("toppingId", toppingId);
			Topping topping = template.queryForObject(sql, param, TOPPING_ROW_MAPPER);
			toppingList.add(topping);
		}
		if(toppingList.size() == 0) {
			return null;
		}
		return toppingList;
	}

}
