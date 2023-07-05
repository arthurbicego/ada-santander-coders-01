package tech.ada.controller;

import tech.ada.view.GroceryView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GroceryController {
    private final Scanner scanner = new Scanner(System.in);
    private final Path path = Paths.get("src/tech/ada/products.txt");

    public void listProducts() throws IOException {
        List<String> products = Files.readAllLines(path);
        products.forEach(product -> GroceryView.showProduct(product));
    }

    public String searchProductById(String id) throws IOException {
        if (verifyIdFound(id)) {
            List<String> products = Files.readAllLines(path);
            for (String productLine : products) {
                String[] split = productLine.split("\\|");
                if (Objects.equals(split[0], String.valueOf(id))) {
                    GroceryView.showProduct(productLine);
                    return productLine;
                }
            }
        }
        return null;
    }

    public Boolean verifyIdFound(String id) throws IOException {
        boolean idFound = false;
        List<String> products = Files.readAllLines(path);
        for (String productLine : products) {
            String[] split = productLine.split("\\|");
            if (Objects.equals(split[0], id)) {
                idFound = true;
            }
        }
        if (!idFound) {
            System.out.println("Id not found.");
            System.out.println();
            return false;
        } else {
            return true;
        }
    }

    public void searchProductByName(String name) throws IOException {
        if (verifyNameFound(name)) {
            List<String> products = Files.readAllLines(path);
            for (String productLine : products) {
                String[] split = productLine.split("\\|");
                if (split[1].toUpperCase().contains(name.toUpperCase())) {
                    GroceryView.showProduct(productLine);
                }
            }
        }
    }

    public Boolean verifyNameFound(String name) throws IOException {
        boolean nameFound = false;
        List<String> products = Files.readAllLines(path);
        for (String productLine : products) {
            String[] split = productLine.split("\\|");
            if (split[1].toUpperCase().contains(name.toUpperCase())) {
                nameFound = true;
            }
        }
        if (!nameFound) {
            System.out.println("Name not found.\n");
            return false;
        } else {
            return true;
        }
    }

    public Boolean verifyAreYouSure(String id, String method) throws IOException {
        System.out.println("Are you sure you want to " + method + " Product " + id + "?");
        System.out.println("1. Yes.");
        System.out.println("2. No! Go back to menu.");
        String choice = scanner.nextLine();
        System.out.println();
        return Objects.equals(choice, "1");
    }


    public Boolean verifyQuantity(String id, int quantity) throws IOException {
        List<String> products = Files.readAllLines(path);
        for (String productLine : products) {
            String[] split = productLine.split("\\|");
            if (Objects.equals(split[0], String.valueOf(id))) {
                if (quantity > Integer.parseInt(split[2])) {
                    System.out.println();
                    System.out.println("Quantity above stock.\n");
                    return false;
                }
            }
        }
        return true;
    }
}
