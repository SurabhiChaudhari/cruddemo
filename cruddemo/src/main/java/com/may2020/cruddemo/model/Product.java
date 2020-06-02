package com.may2020.cruddemo.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;

    @NotNull
    private String name;

    private String specDetails;
    // spec_details
    @NotNull
    private Double price;
    public Product(Long id, @NotNull String name, String specDetails, @NotNull Double price) {
        this.id = id;
        this.name = name;
        this.specDetails = specDetails;
        this.price = price;
    }
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<Order> orders;

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Product(){

    }

    public Product(Long id, @NotNull String name, String specDetails, @NotNull Double price, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.specDetails = specDetails;
        this.price = price;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecDetails() {
        return specDetails;
    }

    public void setSpecDetails(String specDetails) {
        this.specDetails = specDetails;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}