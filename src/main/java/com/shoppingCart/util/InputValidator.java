package com.shoppingCart.util;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    public static String getValidName(Scanner scanner,String name) {
        Matcher matcher;
        do {
            name=name.trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
            }
            Pattern pt=Pattern.compile("^[A-Za-z][A-Za-z0-9 ]{2,19}$");
            matcher=pt.matcher(name);
            if(!matcher.matches()){
                System.out.println("enter valid name");
                name=scanner.nextLine();
            }

        } while (name.isEmpty()|| !matcher.matches());
        return name;
    }

    public static double getValidPrice(Scanner scanner) {
        double price = -1;
        while (price <= 0) {

            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.println("Price must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return price;
    }

    public static int getValidQuantity(Scanner scanner) {
        int qty = -1;
        while (qty <= 0|| qty>100) {

            try {
                qty = Integer.parseInt(scanner.nextLine());
                if (qty <= 0) {
                    System.out.println("Quantity must be a positive integer.");
                }
                else if(qty>100)
                {
                    System.out.println("quantity must be between 1-100");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return qty;
    }

    public static int getValidOption(Scanner scanner, int min, int max) {
        int option = -1;
        while (option < min || option > max) {
            System.out.print("Please choose an option: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
                if (option < min || option > max) {
                    System.out.println("Option must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return option;
    }
}
