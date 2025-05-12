package com.shoppingCart.repository;
import com.shoppingCart.model.Product;
import com.shoppingCart.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    private static final String FILE_PATH = "cart_data.ser";
    private List<Product> cart;

    public CartRepository() {
        this.cart = FileUtil.readFromFile(FILE_PATH);
    }

    public List<Product> getCart() {
        return cart;
    }
    public void clearCart() {
        cart.clear();
        FileUtil.writeToFile(FILE_PATH,cart);
    }

    public void saveCart() {
        FileUtil.writeToFile(FILE_PATH, cart);
    }
}
