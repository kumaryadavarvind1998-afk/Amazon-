package com.amzon.controller;

import com.amzon.repository.OrderRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amzon.entity.OrderEntity;
import com.amzon.request.OrderRequest;
import com.amzon.service.OrderService;

@RestController
@RequestMapping("details")
public class OrderController 
{
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderService orderService;	
	
	// Create -- Creating order
	
	@PostMapping("/order")
	public OrderEntity createOrder(@RequestBody OrderRequest orderRequest)
	{
		return orderService.createOrder(orderRequest);
	}
	
	// Read -- Reading all users
	
	@GetMapping("/order")
	public List<OrderEntity> getAllUsers()
	{
		return orderRepository.findAll();
	}
	
	// Read -- Reading all users
	
	@GetMapping("/details/{order}")
	public OrderEntity getOrderById(@PathVariable int orderId)
	{
		return orderService.getOrderById(orderId);
	}
	
	// Update --Updating the users
	
	@PutMapping("/order/{orderId}")
	public OrderEntity updateOrder(@PathVariable int orderId,@RequestBody OrderRequest orderRequest)
	{
	    return orderService.updateOrder(orderId, orderRequest);
	}
	// Delete --Delete the user
	
	@DeleteMapping("/orders/{orderId}")
	public String deleteOrder(@PathVariable int orderId)
	{
	    return orderService.deleteOrder(orderId);
	}
}
