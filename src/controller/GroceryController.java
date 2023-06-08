package controller;

import entity.Product;
import view.GroceryView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GroceryController {
    private static final Scanner scanner = new Scanner(System.in);
    GroceryView groceryView = new GroceryView();
    Path path = Paths.get("src/products.txt");

    public void listProducts() throws IOException {
        List<String> products = Files.readAllLines(path);
        products.forEach(product -> groceryView.showProduct(product));
    }

    public String searchProductById(int id) throws IOException {
        verifyIdFound(id);
        List<String> products = Files.readAllLines(path);
        for (String productLine : products) {
            String[] split = productLine.split("\\|");
            if (Objects.equals(split[0], String.valueOf(id))) {
                groceryView.showProduct(productLine);
                return productLine;
            }
        }
        return null;
    }

    public void searchProductByName(String name) throws IOException {
        verifyNameFound(name);
        List<String> products = Files.readAllLines(path);
        for (String productLine : products) {
            String[] split = productLine.split("\\|");
            if (split[1].toUpperCase().contains(name.toUpperCase())) {
                groceryView.showProduct(productLine);
            }
        }
    }

    public void verifyAreYouSure(int id, String method) throws IOException {
        System.out.println("Are you sure you want to " + method + " Product " + id + "?");
        System.out.println("1. Yes.");
        System.out.println("2. No! Go back to menu.");
        int choice = scanner.nextInt();
        System.out.println();

        if (choice != 1) {
            groceryView.menuMain();
        }
    }

    public void verifyIdFound(int id) throws IOException {
        boolean idFound = false;
        List<String> products = Files.readAllLines(path);
        for (String productLine : products) {
            String[] split = productLine.split("\\|");
            if (Objects.equals(split[0], String.valueOf(id))) {
                idFound = true;
            }
        }
        if (!idFound) {
            System.out.println("\nId not found.\n");
            groceryView.menuMain();
        }
    }

    public void verifyNameFound(String name) throws IOException {
        boolean nameFound = false;
        List<String> products = Files.readAllLines(path);
        for (String productLine : products) {
            String[] split = productLine.split("\\|");
            if (split[1].toUpperCase().contains(name.toUpperCase())) {
                nameFound = true;
            }
        }
        if (!nameFound) {
            System.out.println("\nName not found.\n");
            groceryView.menuMain();
        }
    }

    public void verifyQuantity(int id, int quantity) throws IOException {
        List<String> products = Files.readAllLines(path);
        for (String productLine : products) {
            String[] split = productLine.split("\\|");
            if (Objects.equals(split[0], String.valueOf(id))) {
                if (quantity > Integer.parseInt(split[2])) {
                    System.out.println("\nQuantity above stock.\n");
                    groceryView.menuMain();
                }
            }
        }
    }

}
