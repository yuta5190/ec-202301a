package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * Usersテーブルを操作するリポジトリ.
 * 
 * @author matsuokatoshiichi
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/** Userオブジェクトを生成する為のローマッパー */
	private final static RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {

		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));

		return user;

	};

	/**
	 * usersテーブルに入力されたUser情報を挿入する.
	 * 
	 * @param user 入力されたUser情報
	 */
	public void insert(User user) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(user);

		String insertSql = "INSERT INTO users(name,email,password,zipcode,address,telephone)VALUES(:name,:email,:password,:zipcode,:address,:telephone);";

		template.update(insertSql, param);
	}

	/**
	 * メールアドレスの重複チェックをする.
	 * 
	 * @param mailAddress 入力されたメールアドレス
	 * @return 存在しない場合はnullを返します
	 */
	public User findByMailAddress(String email) {

		String sql = "SELECT id,name,email,password,zipcode,address,telephone FROM users WHERE email=:email;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	public User findByMailAddressAndPassword(String email,String password) {
		String sql = "SELECT id,name,email,password,zipcode,address,telephone FROM users WHERE email=:email AND password=:password;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password",password);

		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

}
