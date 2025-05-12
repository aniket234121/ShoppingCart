package com.shoppingCart.controller;


import java.util.Scanner;
import com.shoppingCart.service.ProductServices;
import com.shoppingCart.util.InputValidator;

public class ShoppingCartController {
    private ProductServices productServices;
    private Scanner scanner;


    public ShoppingCartController() {
        this.productServices=new ProductServices();
        this.scanner=new Scanner(System.in);
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
                case 1 -> productServices.addProduct();
                case 2 -> productServices.removeProduct();
                case 3 -> productServices.displayCart();
                case 4 -> productServices.displayAmount();
                case 5 -> productServices.clearCart();
                case 6 -> System.out.println("Thank you for shopping!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }


}

