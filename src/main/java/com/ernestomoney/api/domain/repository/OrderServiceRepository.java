package com.ernestomoney.api.domain.repository;

import com.ernestomoney.api.domain.model.OrderService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository <OrderService, Long>{
   
}
