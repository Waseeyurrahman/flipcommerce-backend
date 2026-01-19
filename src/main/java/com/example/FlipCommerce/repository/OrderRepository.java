package com.example.FlipCommerce.repository;

import com.example.FlipCommerce.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findTop5ByOrderByTotalValueDesc();

    List<OrderEntity> findByCustomerId(Integer customerId);

    List<OrderEntity> findTop5ByCustomerIdOrderByTotalValueDesc(Integer customerId);;
}
