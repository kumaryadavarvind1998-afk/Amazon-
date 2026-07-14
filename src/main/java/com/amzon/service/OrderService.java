package com.amzon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amzon.entity.OrderEntity;
import com.amzon.entity.UserEntity;
import com.amzon.repository.OrderRepository;
import com.amzon.repository.UserRepository;
import com.amzon.request.OrderRequest;

@Service
public class OrderService 
{
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	// Creating the order
	
	public OrderEntity createOrder(OrderRequest orderRequest)
	{
		UserEntity user=userRepository.findById(orderRequest.getUserId()).orElse(null);
		
		if(user == null)
		{
			return null;
		}
		
		
		OrderEntity orderEntity=new OrderEntity();
		
		orderEntity.setProductName(orderRequest.getProductName());
		orderEntity.setQuantity(orderRequest.getQuantity());
		orderEntity.setAmount(orderRequest.getAmount());
		orderEntity.setOrderStatus(orderRequest.getOrderStatus());
		
		orderEntity.setUser(user);
		
		return orderRepository.save(orderEntity);
	}
	
	// Read -- get all orders
	
	public List<OrderEntity> getAllOrders()
	{
		return orderRepository.findAll();
	}

	// Read -- get order by id
	
	public OrderEntity getOrderById(int orderId)
	{
		return orderRepository.findById(orderId).orElse(null);
	}
	
	
	// Update -- Update orders
	
	public OrderEntity updateOrder(int orderId,OrderRequest orderRequest)
	{
		OrderEntity entity=orderRepository.findById(orderId).orElse(null);
		
		if(entity==null)
		{
			return null;
		}
		
		entity.setProductName(orderRequest.getProductName());
		entity.setQuantity(orderRequest.getQuantity());
		entity.setAmount(orderRequest.getAmount());
		entity.setOrderStatus(orderRequest.getOrderStatus());
		
		return orderRepository.save(entity);
	}
	
	// Delete Order
	
	public String deleteOrder(int orderId)
	{
		orderRepository.deleteById(orderId);
		
		return "Order deleted successfully";
	}
	
}
