package com.JunitPractise.entity;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;

    public Products(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Products() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
