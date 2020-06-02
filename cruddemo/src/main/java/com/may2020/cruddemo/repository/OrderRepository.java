package com.may2020.cruddemo.repository;

import com.may2020.cruddemo.model.Order;
import com.may2020.cruddemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    public List<Order> findByProduct(Product product);
}