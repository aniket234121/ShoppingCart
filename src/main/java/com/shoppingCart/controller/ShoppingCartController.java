package com.shoppingCart.controller;

import java.util.List;
import java.util.Scanner;
import com.shoppingCart.model.Product;
import com.shoppingCart.service.ShoppingCartService;
import com.shoppingCart.repository.CartRepository;
import com.shoppingCart.util.InputValidator;

public class ShoppingCartController {
    private final ShoppingCartService cartService;
    private final Scanner scanner;

    public ShoppingCartController() {
        this.cartService = new ShoppingCartService(new CartRepository());
        this.scanner = new Scanner(System.in);

    }

    public void start() {
        int choice;
        do {
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Display Cart");
            System.out.println("4. Get Cart Amount");
            System.out.println("5. Clear Cart");
            System.out.println("6. Exit");


            choice = InputValidator.getValidOption(scanner,1,6);
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> displayCart();
                case 4 -> displayAmount();
                case 5 -> {
                    cartService.clearCart();
                    System.out.println("Cart has been cleared.");
                }
                case 6 -> System.out.println("Thank you for shopping!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private void addProduct() {
        System.out.print("Enter product name: ");
        String name = InputValidator.getValidName(scanner,scanner.nextLine());
        if(cartService.isItemInCart(name))
        {
            System.out.println("Product is already in cart");
            return;
        }
        System.out.print("Enter product price: ");
        double price =InputValidator.getValidPrice(scanner);
        System.out.print("Enter product quantity: ");
        int qty =InputValidator.getValidQuantity(scanner);

        Product product = new Product(name, price, qty);
        cartService.addProduct(product);
        System.out.println("Product added to cart!");
    }

    private void removeProduct() {

        if(displayCart()){
            System.out.print("Enter product name to remove: ");
            String name = scanner.nextLine();
            if (cartService.removeProduct(name)) {
                System.out.println("Product removed from cart!");
            } else {
                System.out.println("Product not found.");
            }
        }
    }

    private boolean displayCart() {
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

    private void displayAmount() {
        double total = cartService.getCartAmount();
        System.out.println("Total cart amount: " + total+'\u20B9');
    }
}

