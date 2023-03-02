package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.ShoppingCartForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

@Service
@Transactional
public class ShoppingCartService {

	 @Autowired
	 private OrderRepository orderRepository;
	 @Autowired
	 private OrderItemRepository orderItemRepository;
	 @Autowired
	 private OrderToppingRepository orderToppingRepository;

	 /**
	 * オーダー情報を取得する.
	 * 
	 * @param userId ユーザーID
	 * @return オーダー情報
	 */
	public Order load(Integer userId) {
		 return orderRepository.load(userId);
	 }
	 
	 /**
	 * オーダー情報を挿入する.
	 * 
	 * @param shoppingCartForm
	 * @param userId
	 * @param orderItemId
	 */
	public void insert(ShoppingCartForm shoppingCartForm, Integer userId, Integer orderItemId) {
		 Integer status = 0;
		 System.out.println("shoppingService内");
		 System.out.println(shoppingCartForm);
		 System.out.println(orderRepository.findByUserIdAndStatus(userId, status));
		 if(orderRepository.findByUserIdAndStatus(userId, status) != null) {
			 System.out.println("ここまで正常動作");
			 orderItemRepository.insert(shoppingCartForm, orderItemId);
			 orderToppingRepository.insert(shoppingCartForm, orderItemId);
		 }else {
			 Order order = new Order();
			 order.setUserId(userId);
			 order.setStatus(status);
			 orderRepository.insert(order);
			 orderItemRepository.insert(shoppingCartForm, userId);
			 orderToppingRepository.insert(shoppingCartForm, orderItemId);
		 }
	 }
	 
	 /**
	 * 注文情報を削除する.
	 * 
	 * @param orderItemId 削除対象となる注文商品ID
 	 */
	public void deleteByOrderItemId(Integer orderItemId) {
		 
	 }
}
