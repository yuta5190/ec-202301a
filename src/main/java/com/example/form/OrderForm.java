package com.example.form;

/**
 * 注文者情報フォーム
 * @author yoshidayuuta
 *
 */
public class OrderForm {
	/**注文ID*/
	private Integer id;
	/**ユーザーID*/
	private Integer userId;
	/**注文者名*/
	private String destinationName;
	/**注文者メールアドレス*/
	private String destinationEmail;
	/**注文者郵便番号*/
	private String destinationZipcode;
	/**注文者住所*/
	private String destinationAddress;
	/**注文者電話番号*/
	private String destinationTel;
	/**お届け日*/
	private String orderDate;
	/**お届け時間*/
	private String orderTime;
	/**支払い方法*/
	private Integer paymentMethod;
	/**合計金額*/
	private Integer totalPrice;

	public OrderForm() {
	}

	public OrderForm(Integer id, Integer userId, String destinationName, String destinationEmail,
			String destinationZipcode, String destinationAddress, String destinationTel, String orderDate,
			String orderTime, Integer paymentMethod, Integer totalPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.destinationName = destinationName;
		this.destinationEmail = destinationEmail;
		this.destinationZipcode = destinationZipcode;
		this.destinationAddress = destinationAddress;
		this.destinationTel = destinationTel;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.paymentMethod = paymentMethod;
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderForm [id=" + id + ", userId=" + userId + ", destinationName=" + destinationName
				+ ", destinationEmail=" + destinationEmail + ", destinationZipcode=" + destinationZipcode
				+ ", destinationAddress=" + destinationAddress + ", destinationTel=" + destinationTel + ", orderDate="
				+ orderDate + ", orderTime=" + orderTime + ", paymentMethod=" + paymentMethod + ", totalPrice="
				+ totalPrice + "]";
	}

}
