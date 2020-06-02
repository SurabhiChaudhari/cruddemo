package com.may2020.cruddemo.controller;

import com.may2020.cruddemo.model.Order;
import com.may2020.cruddemo.model.Product;
import com.may2020.cruddemo.repository.OrderRepository;
import com.may2020.cruddemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class OrderController {
    //product id-1
    //ordername +orderdetails

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    //get-> orders of a particular product --> id
    @GetMapping("/product/{productId}/orders")
    List<Order> getOrderByProduct(@PathVariable(name = "productId") Long productId) throws Exception{
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            return orderRepository.findByProduct(product.get());
        }else{
            throw new Exception("Invalid id");
        }
    }
//presenatation layers -> front end
//business logic -> controllers / services
//persistence layers
//data access layer


    //get -> order list of all the orders
    @GetMapping("/orders")
    List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    //post
    @PostMapping("/product/{productId}/orders")
    Order addOrder(@PathVariable(name="productId") Long productId, @Valid @RequestBody Order order) throws Exception{
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            order.setProduct(product.get());
            return orderRepository.save(order);
        }else{
            throw new Exception("Invalid id");
        }
    }


    //put
    @PutMapping("/product/{productId}/orders/{id}")
    Order updateOrder(@PathVariable(name="productId") Long productId, @PathVariable(name="id") Long id,@Valid @RequestBody Order order) throws Exception{
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            Optional<Order> oldOrder = orderRepository.findById(id);
            if(oldOrder.isPresent()){
                oldOrder.get().setOrderDetails(order.getOrderDetails());
                oldOrder.get().setOrderName(order.getOrderName());
                return orderRepository.save(oldOrder.get());
            }else{
                throw new Exception("Invalid order id");
            }
        }else{
            throw new Exception("Invalid product id");
        }
    }

    //

    //delete
    @DeleteMapping("/product/{productId}/orders/{orderId}")
    String deleteOrder(@PathVariable(name = "productId") Long productId,@PathVariable(name = "orderId") Long orderId){
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            Optional<Order> oldOrder = orderRepository.findById(orderId);
            if (oldOrder.isPresent()) {
                orderRepository.deleteById(orderId);
                return "Order Deleted!";
            } else {
                return "Can't delete Order Invalid Order id";
            }
        }else{
            return "Can't delete order because Invalid Product Id";
        }
    }

}