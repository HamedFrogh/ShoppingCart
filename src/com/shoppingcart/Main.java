package com.shoppingcart;

import com.shoppingcart.adapter.Product;
import com.shoppingcart.strategy.CashOnDeliveryPayment;
import com.shoppingcart.strategy.CreditCardPayment;
import com.shoppingcart.strategy.PayPalPayment;
import com.shoppingcart.strategy.PaymentStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = ShoppingCart.getInstance();

        System.out.println("Welcome to the Online Shopping Cart!");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a product to the cart");
            System.out.println("2. View cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProductToCart(scanner, cart);
                    break;
                case 2:
                    viewCart(cart);
                    break;
                case 3:
                    checkout(cart);
                    break;
                case 4:
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProductToCart(Scanner scanner, ShoppingCart cart) {
        System.out.print("Enter product name: ");
        String productName = scanner.next();
        System.out.print("Enter product price: ");
        double productPrice = scanner.nextDouble();
        Product product = ProductFactory.createProduct(productName, productPrice);
        cart.addProduct(product);
        System.out.println(productName + " added to the cart.");
    }

    private static void viewCart(ShoppingCart cart) {
        System.out.println("Your Shopping Cart:");
        System.out.println("Total: $" + cart.getTotal());
        // List cart contents.
    }

    private static void checkout(ShoppingCart cart) {
        System.out.println("Select a payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Cash on Delivery");
        Scanner scanner = new Scanner(System.in);
        int paymentChoice = scanner.nextInt();

        PaymentStrategy paymentStrategy;

        switch (paymentChoice) {
            case 1:
                paymentStrategy = new CreditCardPayment ();
                break;
            case 2:
                paymentStrategy = new PayPalPayment ();
                break;
            case 3:
                paymentStrategy = new CashOnDeliveryPayment ();
                break;
            default:
                System.out.println("Invalid payment method. Please try again.");
                return;
        }

        paymentStrategy.pay(cart.getTotal());
        System.out.println("Payment successful. Thank you for your purchase!");
    }
}
