package com.example.domain;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 注文のドメインクラス
 * 
 * @author yoshidayuuta
 *
 */
public class Order {
	/** id */
	private Integer id;
	/** ユーザーid */
	private Integer userId;
	/** 注文状況 */
	/** 0.注文前
	 * 　1.未入金
	 * 　2.入金済み
	 * 　3.発送済み*/
	private Integer status;
	/** 合計金額 */
	private Integer totalPrice;
	/** 注文日 */
	private Date orderDate;
	/** 宛先氏名 */
	private String destinationName;
	/** 宛先Eメール */
	private String destinationEmail;
	/** 宛先郵便番号 */
	private String destinationZipcode;
	/** 宛先住所 */
	private String destinationAddress;
	/** 宛先TEL */
	private String destinationTel;
	/** 配達時間 */
	private Timestamp deliveryTime;
	/** 支払い方法 */
	private Integer paymentMethod;
	/** ユーザー */
	private User user;
	/** 注文商品リスト */
	private List<OrderItem> orderItemList;

	/**
	 * 消費税を計算するメソッド
	 * 
	 * @return 消費税
	 */
	public int getTAX() {
		return (int) (CalcTotalPrice() * 0.1);
	}

	/**
	 * 注文金額を表示するメソッド
	 * 
	 * @return 注文金額
	 */
	public int CalcTotalPrice() {
		int total = 0;
		for (List<Orderitem> orderItem : orderItemList) {
			total += orderItem.getSubTotal();
		}

	}
}
