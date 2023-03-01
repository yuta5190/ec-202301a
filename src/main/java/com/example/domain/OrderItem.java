package com.example.domain;

import java.util.List;

/**
 * 注文商品情報を表すドメインクラス.
 * 
 * @author seiji_kitahara
 *
 */
public class OrderItem {

	/** 注文商品ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ情報 */
	private Character size;
	// TODO マージ後Item部分のコンパイルエラーが消えるか確認
	/** 商品情報 */
	private Item item;
	/** 注文トッピング情報リスト */
	private List<OrderTopping> orderToppingList;

	public OrderItem() {
	}

	public OrderItem(Integer id, Integer itemId, Integer orderId, Integer quantity, Character size, Item item,
			List<OrderTopping> orderToppingList) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.size = size;
		this.item = item;
		this.orderToppingList = orderToppingList;
	}

	/**
	 * 各注文商品の小計を計算する.
	 * 
	 * @return サイズごとの小計計算結果
	 */
	public int getSubTotal() {
		int subTotal = 0;
		if ('M' == (this.size)) {
			for (OrderTopping ordertopping : this.orderToppingList) {
				subTotal = ordertopping.getTopping().getPriceM();
			}
			subTotal = this.item.getPriceM() * this.quantity;

		} else if ('L' == (this.size)) {
			for (OrderTopping topping : this.orderToppingList) {
				subTotal = topping.getTopping().getPriceL();
			}
			subTotal = this.item.getPriceL() * this.quantity;
		}
		return subTotal;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", quantity=" + quantity + ", size=" + size
				+ ", orderToppingList=" + orderToppingList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
