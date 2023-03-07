package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.ShoppingCartForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

/**
 * ショッピングカート情報を操作するサービスクラス.
 * 
 * @author seiji_kitahara
 *
 */
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
	 * 注文前のオーダー情報を取得する.
	 * 
	 * @param userId ユーザーID
	 * @return オーダー情報
	 */
	public Order load(Integer userId) {
		Order order = new Order();
		order = orderRepository.load(userId, 0);
		return order;
	}

	/**
	 * オーダー情報を挿入する.
	 * 
	 * @param shoppingCartForm
	 * @param userId
	 * @param orderItemId
	 */
	public void insert(ShoppingCartForm shoppingCartForm, Integer userId) {
		Integer status = 0;
		if (orderRepository.findByUserIdAndStatus(userId, status) != null) {
			Integer orderId = orderRepository.findByUserIdAndStatus(userId, status).getId();
			orderItemRepository.insert(shoppingCartForm, orderId);
			Integer orderItemId = orderItemRepository.load(orderId).getId();
			if (shoppingCartForm.getToppingIdList() != null) {
				orderToppingRepository.insert(shoppingCartForm, orderItemId);
			}
		} else {
			orderRepository.insert(userId, status);
			Integer orderId = orderRepository.findByUserIdAndStatus(userId, status).getId();
			orderItemRepository.insert(shoppingCartForm, orderId);
			Integer orderItemId = orderItemRepository.load(orderId).getId();
			if (shoppingCartForm.getToppingIdList() != null) {
				orderToppingRepository.insert(shoppingCartForm, orderItemId);
			}
		}
	}

	/**
	 * 注文情報を削除する.
	 * 
	 * @param orderItemId 削除対象となる注文商品ID
	 */
	public void deleteByOrderItemId(Integer orderItemId) {
		orderItemRepository.delete(orderItemId);
	}
}
