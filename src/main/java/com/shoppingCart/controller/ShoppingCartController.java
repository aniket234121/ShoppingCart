package com.shoppingCart.controller;

import java.util.List;
import java.util.Scanner;
import com.shoppingCart.model.Product;
import com.shoppingCart.service.ShoppingCartService;
import com.shoppingCart.repository.CartRepository;
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
            System.out.println("\nOptions:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Display Cart");
            System.out.println("4. Get Cart Amount");
            System.out.println("5. Exit");
            System.out.print("Please choose an option: ");

            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addProductFlow();
                case 2 -> removeProductFlow();
                case 3 -> displayCartFlow();
                case 4 -> displayAmountFlow();
                case 5 -> System.out.println("Thank you for shopping!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private void addProductFlow() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter product quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());

        Product product = new Product(name, price, qty);
        cartService.addProduct(product);
        System.out.println("Product added to cart!");
    }

    private void removeProductFlow() {
        System.out.print("Enter product name to remove: ");
        String name = scanner.nextLine();
        if (cartService.removeProduct(name)) {
            System.out.println("Product removed from cart!");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void displayCartFlow() {
        List<Product> cart = cartService.getCartItems();
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            int i = 1;
            for (Product product : cart) {
                System.out.println(i++ + ". " + product);
            }
        }
    }

    private void displayAmountFlow() {
        double total = cartService.getCartAmount();
        System.out.println("Total cart amount: " + total+'\u20B9');
    }
}

