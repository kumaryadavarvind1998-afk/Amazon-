package com.amzon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amzon.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>
{
	
}
