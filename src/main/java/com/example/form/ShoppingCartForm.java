package com.example.form;

import java.util.List;

import com.example.domain.Topping;

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
	/** サイズ（MとLが存在）*/
	private Character size;
	/** 追加するトッピング情報のリスト */
	private List<Topping> toppingList;
	
	public ShoppingCartForm() {}
	
	public ShoppingCartForm(Integer itemId, Integer quantity, Character size, List<Topping> toppingList) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.size = size;
		this.toppingList = toppingList;
	}
	@Override
	public String toString() {
		return "ShoppingCartForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", toppingList="
				+ toppingList + "]";
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
	public List<Topping> getToppingList() {
		return toppingList;
	}
	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}


}
