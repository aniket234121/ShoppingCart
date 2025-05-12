package com.shoppingCart.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private double price;
    private int quantity;

    public Product() {}

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return name + " | Price:"+'\u20B9'+ price + " | Quantity: " + quantity + " | Total: " +'\u20B9'+ getTotalPrice();
    }
}

