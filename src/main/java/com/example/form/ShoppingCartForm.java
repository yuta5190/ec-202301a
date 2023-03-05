package com.example.form;

import java.util.List;
/**
 * 商品詳細ページより選択された値を受け取るフォームクラス.
 * 
 * @author seiji_kitahara
 *
 */
public class ShoppingCartForm {

	/** 商品ID */
	private Integer itemId;
	/** 量 */
	private Integer quantity;
	/** サイズ（MとLが存在） */
	private Character size;
	/** 追加するトッピングのIDを詰めてあげるリスト */
	private List<Integer> toppingIdList;

	public ShoppingCartForm() {
	}

	public ShoppingCartForm(Integer itemId, Integer quantity, Character size, List<Integer> toppingIdList) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.size = size;
		this.toppingIdList = toppingIdList;
	}

	@Override
	public String toString() {
		return "ShoppingCartForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", toppingIdList="
				+ toppingIdList + "]";
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public List<Integer> getToppingIdList() {
		try {
		}catch(NullPointerException e) {
			System.err.println("システムエラーが発生しました");
		}
		return toppingIdList;			
	}

	public void setToppingIdList(List<Integer> toppingIdList) {
		this.toppingIdList = toppingIdList;
	}

}
