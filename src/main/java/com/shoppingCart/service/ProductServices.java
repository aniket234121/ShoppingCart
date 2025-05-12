package com.shoppingCart.service;

import com.shoppingCart.model.Product;
import com.shoppingCart.repository.CartRepository;
import com.shoppingCart.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class ProductServices {

    private final ShoppingCartService cartService;
    private final Scanner scanner;

    public ProductServices() {
        this.cartService = new ShoppingCartService(new CartRepository());
        this.scanner = new Scanner(System.in);

    }
    public void clearCart(){
        cartService.clearCart();
        System.out.println("Cart has been cleared.");
    }
    public void addProduct() {
        System.out.print("Enter product name: ");
        String name = InputValidator.getValidName(scanner, scanner.nextLine());
        if (cartService.isItemInCart(name)) {
            System.out.println("Product is already in cart");
            return;
        }
        System.out.print("Enter product price: ");
        double price = InputValidator.getValidPrice(scanner);
        System.out.print("Enter product quantity: ");
        int qty = InputValidator.getValidQuantity(scanner);

        Product product = new Product(name, price, qty);
        cartService.addProduct(product);
        System.out.println("Product added to cart!");
    }

    public void removeProduct() {

        if (displayCart()) {
            System.out.print("Enter product name to remove: ");
            String name = scanner.nextLine();
            if (cartService.removeProduct(name)) {
                System.out.println("Product removed from cart!");
            } else {
                System.out.println("Product not found.");
            }
        }
    }

    public boolean displayCart() {
        List<Product> cart = cartService.getCartItems();
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return false;
        } else {
            int serial = 1;
            for (Product product : cart) {
                System.out.println(serial++ + ". " + product);
            }
        }
        System.out.println();
        return true;
    }

    public void displayAmount() {
        double total = cartService.getCartAmount();
        System.out.println("Total cart amount: " + total + '\u20B9');
    }
}
