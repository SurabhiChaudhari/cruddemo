package com.may2020.cruddemo.controller;

import com.may2020.cruddemo.model.Product;
import com.may2020.cruddemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @PostMapping("/product")
    Product addProducts(@Valid @RequestBody Product product) {

        return productRepository.save(product);
    }

    @PutMapping("/product/{id}")
    Product updateProduct(@PathVariable(name = "id") Long id, @Valid @RequestBody Product product) throws Exception {
        Optional<Product> oldProduct = productRepository.findById(id);
        if (oldProduct.isPresent()) {
            //product consists of new values -> oldProduct
            oldProduct.get().setName(product.getName());
            oldProduct.get().setPrice(product.getPrice());
            oldProduct.get().setSpecDetails(product.getSpecDetails());
            //.save
            return productRepository.save(oldProduct.get());
        } else {
            throw new Exception("Invalid id");
        }


    }

    @GetMapping("/productname")
    List<Product> getProductByName(@RequestParam(name = "name") String name, @RequestParam(name = "spec") String spec) {
//        String name = "cookies";
        return productRepository.findByNameAndSpec(name, spec);
    }

    @GetMapping("/orderedproducts")
    List<Product> getProductOrderByName() {
        return productRepository.findAllByOrderName();
    }

    @GetMapping("/productnames")
    List<Integer> getProductNames(@RequestParam(name = "name") String name) {
        return productRepository.findByNameParam(name);
    }


    @DeleteMapping("/product/{id}")
    String deleteProduct(@PathVariable Long id) throws Exception{
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return  "Deleted!";
        }else{
            throw new Exception("Invalid id");
        }
    }
}