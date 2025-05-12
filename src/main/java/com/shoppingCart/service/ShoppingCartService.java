package com.shoppingCart.service;

import com.shoppingCart.model.Product;
import com.shoppingCart.*;
import com.shoppingCart.repository.CartRepository;
import java.util.Iterator;
import java.util.List;

public class ShoppingCartService {
    private final CartRepository cartRepo;

    public ShoppingCartService(CartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    public void addProduct(Product product) {
        cartRepo.getCart().add(product);
        cartRepo.saveCart();
    }

    public boolean removeProduct(String name) {
        Iterator<Product> iterator = cartRepo.getCart().iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                cartRepo.saveCart();
                return true;
            }
        }
        return false;
    }
    public void clearCart() {
        cartRepo.clearCart();
    }
    public boolean isItemInCart(String name){
      if( cartRepo.getCart().stream().filter(item->item.getName().equals(name)).findAny().orElse(null)==null)
      {
          return false;
      }
      return true;
    }

    public List<Product> getCartItems() {
        return cartRepo.getCart();
    }

    public double getCartAmount() {
        return cartRepo.getCart().stream()
                .mapToDouble(Product::getTotalPrice)
                .sum();
    }
}

