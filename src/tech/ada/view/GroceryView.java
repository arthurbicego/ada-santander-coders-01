package tech.ada.view;

import tech.ada.controller.AdminController;
import tech.ada.controller.CustomerController;
import tech.ada.controller.GroceryController;

import java.io.IOException;
import java.util.Scanner;


public class GroceryView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerController customerController = new CustomerController();
    private static final AdminController adminController = new AdminController();
    private static final GroceryController groceryController = new GroceryController();

    public static void menuMain() throws IOException {
        boolean menuLoop = true;

        while (menuLoop) {
            System.out.println("Main Menu:");
            System.out.println("1. Customer Menu");
            System.out.println("2. Admin Menu");
            System.out.println("0. Exit");
            String choice = scanner.nextLine();
            System.out.println();
            switch (choice) {
                case "1" -> {
                    menuCustomer();
                }
                case "2" -> {
                    menuAdmin();
                }
                case "0" -> {
                    System.out.println("Thank you for access our Grocery Store! See you.");
                    menuLoop = false;
                }
                default -> {
                    System.out.println("Error. Enter one number option.");
                    System.out.println();
                }
            }
        }
    }

    public static void menuCustomer() throws IOException {
        boolean menuLoop = true;

        while (menuLoop) {
            System.out.println("Customer Menu:");
            System.out.println("1. List Products");
            System.out.println("2. Search Product by Id");
            System.out.println("3. Search Product by Name");
            System.out.println("4. Buy Product (Id)");
            System.out.println("5. Remove Product from Shopping Cart");
            System.out.println("6. Checkout");
            System.out.println("0. Main Menu");
            String choice = scanner.nextLine();
            System.out.println();
            switch (choice) {
                case "1" -> {
                    groceryController.listProducts();
                }
                case "2" -> {
                    System.out.println("Enter the Id you want to search.");
                    String id = scanner.nextLine();
                    System.out.println();
                    groceryController.searchProductById(id);
                }
                case "3" -> {
                    System.out.println("Enter the Product Name you want to search.");
                    String name = scanner.nextLine();
                    System.out.println();
                    groceryController.searchProductByName(name);
                }
                case "4" -> {
                    System.out.println("Enter the Id of Product you want to buy.");
                    String id = scanner.nextLine();
                    System.out.println();
                    customerController.buyProduct(id);
                    customerController.showShoppingCart();
                }
                case "5" -> {
                    customerController.showShoppingCart();
                    System.out.println("Enter the Id of product you want to remove from Shopping Cart: ");
                    String id = scanner.nextLine();
                    System.out.println();
                    customerController.removeProductShoppingCart(id);
                }
                case "6" -> {
                    if (customerController.checkout()) {
                        menuLoop = false;
                    }
                }
                case "0" -> {
                    menuLoop = false;
                }
                default -> {
                    System.out.println("Error. Enter one number option.");
                    System.out.println();
                }
            }
        }
    }

    public static void menuAdmin() throws IOException {
        boolean menuLoop = true;

        while (menuLoop) {
            System.out.println("Admin Menu:");
            System.out.println("1. Register Product");
            System.out.println("2. List Products");
            System.out.println("3. Search Product by Id");
            System.out.println("4. Search Product by Name");
            System.out.println("5. Update Product");
            System.out.println("6. Delete Product");
            System.out.println("0. Main Menu");
            String choice = scanner.nextLine();
            System.out.println();
            switch (choice) {
                case "1" -> {
                    adminController.registerProduct();
                }
                case "2" -> {
                    groceryController.listProducts();
                }
                case "3" -> {
                    System.out.println("Enter the Id you want to search.");
                    String id = scanner.nextLine();
                    System.out.println();
                    groceryController.searchProductById(id);
                }
                case "4" -> {
                    System.out.println("Enter the Product Name you want to search.");
                    String name = scanner.nextLine();
                    System.out.println();
                    groceryController.searchProductByName(name);
                }
                case "5" -> {
                    System.out.println("Enter the product id you want to update.");
                    String id = scanner.nextLine();
                    System.out.println();
                    adminController.updateProduct(id);
                }
                case "6" -> {
                    System.out.println("Enter the product id you want to delete.");
                    String id = scanner.nextLine();
                    System.out.println();
                    adminController.deleteProduct(id);
                }
                case "0" -> {
                    menuLoop = false;
                }
                default -> {
                    System.out.println("Error. Enter one number option.");
                    System.out.println();
                }
            }
        }
    }

    public static void showProduct(String product) {
        String[] split = product.split("\\|");
        System.out.println("Id: " + split[0]);
        System.out.println("Product: " + split[1]);
        System.out.println("Quantity: " + split[2]);
        System.out.println("Price: " + split[3]);
        System.out.println();
    }
}
