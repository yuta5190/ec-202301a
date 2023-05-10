package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.repository.OrderRepository;

@SpringBootTest
public class OrderRepositoryTest {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private NamedParameterJdbcTemplate template;

	// DBに入れるorderの例を作成するメソッド
	public Order ordermade() {
		Order order = new Order();
		Date date = Date.valueOf("2100-01-01");
		Timestamp time = Timestamp.valueOf("2100-01-01 00:00:00");
		order.setId(1000);
		order.setUserId(1000);
		order.setTotalPrice(1000);
		order.setOrderDate(date);
		order.setDestinationName("dummy名");
		order.setDestinationEmail("dummy@xxx.com");
		order.setDestinationZipcode("123-4567");
		order.setDestinationAddress("dummy");
		order.setDestinationTel("080-0000-0000");
		order.setDeliveryTime(time);
		order.setPaymentMethod(1);
		order.setStatus(1);
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		order.setOrderItemList(orderItemList);
		return order;
	}

	// 受け取ったOrderでインサートするメソッド
	public void Insert(Order order) {
		StringBuilder insertSql = new StringBuilder();
		insertSql.append(
				"INSERT INTO orders(id,user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode,destination_address,destination_tel,delivery_time,payment_method");
		insertSql.append(
				") VALUES(:id,:userId, :status, :totalPrice,:orderDate,:destinationName,:destinationEmail,:destinationZipcode,:destinationAddress,:destinationTel,:deliveryTime,:paymentMethod);");
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(insertSql.toString(), param);
	}

	// 受け取ったidでdeleteを実行するメソッド
	public void delete(int userId) {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("id", userId);
		template.update("delete from orders where user_id = :id", param);
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void testSave() throws Exception {
		System.out.println("DB初期化処理開始");
		this.delete(1000);
		// orderの例を作成し、返すメソッド
		Order order = this.ordermade();
		this.Insert(order);
		System.out.println("DB初期化処理終了");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.delete(1000);
	}

	@DisplayName("ユーザ検索正常系")
	@Test
	void testOrderFindByUserIdAndStatustr() {
		System.out.println("ユーザ検索:user_id=1000,status=1");
		Order order = orderRepository.findByUserIdAndStatus(1000, 1);
		assertEquals(this.ordermade().toString(), order.toString(), "TC1:誤った結果です");
		System.out.println("TC1完了");
	}

	@DisplayName("ユーザ検索異常系")
	@ParameterizedTest
	@CsvSource({ "1000,", ",1", ",", "10000,10000", "10000,1", "1000,10000", "1000,0" })
	void testOrderFindByUserIdAndStatusfal(Integer x, Integer y) {
		System.out.println("ユーザ検索:user_id=1000,status=1");
		Order order = orderRepository.findByUserIdAndStatus(x, y);
		assertEquals(null, order, "TC2:誤った結果です");
		System.out.println("TC2完了");
	}

	@DisplayName("注文情報を挿入する 正常系")
	@Test
	void testinserttr() {
		System.out.println("注文情報を注入する。正常系");
		orderRepository.insert(1000, 0);
		Order order = orderRepository.findByUserIdAndStatus(1000, 0);
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("id", 1000);
		template.update("delete from orders where user_id = :id", param);
		Order orderAns = new Order();
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		orderAns.setId(order.getId());
		orderAns.setUserId(1000);
		orderAns.setStatus(0);
		orderAns.setOrderItemList(orderItemList);
		orderAns.setTotalPrice(0);
		orderAns.setPaymentMethod(0);
		assertEquals(orderAns.toString(), order.toString(), "TC3:誤った結果です");
		System.out.println("TC3完了");
	}

	@DisplayName("注文内容更新　正常系")
	@Test
	void testOrderUpdatetr() {
		System.out.println("注文内容更新　正常系");
		Order orderBefore = orderRepository.findByUserIdAndStatus(1000, 1);
		Order order = orderBefore;
		order.setDestinationName("dummy名2");
		order.setDestinationEmail("dummy@test.com");
		orderRepository.update(order);
		Order orderAfter = orderRepository.findByUserIdAndStatus(1000, 1);
		assertEquals(orderAfter.getId(), orderBefore.getId(), "TC4:誤った結果です");
		assertEquals("dummy名2", orderBefore.getDestinationName(), "TC4:誤った結果です");
		assertEquals("dummy@test.com", orderBefore.getDestinationEmail(), "TC4:誤った結果です");
		System.out.println("TC4完了");
	}

	@DisplayName("過去注文情報取得　正常系")
	@Test
	void testHistoryFindtr() {
		System.out.println("過去注文情報取得　正常系");
		List<Order> orderlist = new ArrayList<Order>();
		orderlist = orderRepository.historyFindByUserIdAndStatus(1000, 1);
		assertEquals(this.ordermade().toString(), orderlist.get(0).toString(), "TC5:誤った結果です");
		try {
			orderlist.get(1);
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e, "TC5:誤った結果です");
		}

		System.out.println("TC5完了");
	}
}