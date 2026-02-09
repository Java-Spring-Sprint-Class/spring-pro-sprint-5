package ua.duikt.learning.java.pro.spring.individualfifthsprint.controllers;

import org.springframework.web.bind.annotation.*;
import ua.duikt.learning.java.pro.spring.individualfifthsprint.exceptions.ProductNotFoundException;

/**
 * Created by Mykyta Sirobaba on 20.01.2026.
 * email mykyta.sirobaba@gmail.com
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id) {
        if (id == 99) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        return "Product " + id;
    }

    @GetMapping("/update")
    public String updatePrice(@RequestParam double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return "Price updated to " + price;
    }

    @GetMapping("/dangerous")
    public String dangerousAction() {
        throw new RuntimeException("Something went wrong internally!");
    }
}