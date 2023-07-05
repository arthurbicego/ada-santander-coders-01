package tech.ada.controller;

import tech.ada.entity.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private final Scanner scanner = new Scanner(System.in);
    private final GroceryController groceryController = new GroceryController();
    private final Path path = Paths.get("src/tech/ada/products.txt");

    public void registerProduct() throws IOException {
        Product product = new Product();
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        int size = Files.readAllLines(path).size();
        if (size == 0) {
            product.setId("0");
        } else {
            List<String> products = Files.readAllLines(path);
            size--;
            String[] split = products.get(size).split("\\|");
            Integer valueOf = Integer.valueOf(split[0]);
            valueOf++;
            product.setId(String.valueOf(valueOf));
        }
        System.out.println("Enter the name of the product.");
        product.setName(scanner.nextLine());

        System.out.println("Enter the quantity of the product.");
        try {
            product.setQuantity(scanner.nextInt());
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error registering the quantity. Default quantity (1) has been set.");
            product.setQuantity(1);
            System.out.println();
        }

        System.out.println("Enter the price of the product.");

        try {
            product.setPrice(scanner.nextDouble());
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error registering the price. Default price (1.00) has been set.");
            product.setPrice(1.00);
            System.out.println();
        }
        Files.writeString(path, product.toString(), StandardOpenOption.APPEND);
    }

    public void updateProduct(String id) throws IOException {
        String productLine = groceryController.searchProductById(id);
        if (productLine != null) {
            if (groceryController.verifyAreYouSure(id, "update")) {
                String[] split = productLine.split("\\|");
                List<String> products = Files.readAllLines(path);
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).equals(productLine)) {
                        split[0] = id;
                        System.out.println("Enter the new Product Name:");
                        split[1] = scanner.nextLine();
                        System.out.println("Enter the new Product Quantity:");
                        try {
                            split[2] = String.valueOf(scanner.nextInt());
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println("Error registering the quantity. Default quantity (1) has been set.");
                            split[2] = String.valueOf(1);
                            System.out.println();
                        }

                        System.out.println("Enter the new Product Price:");
                        try {
                            split[3] = String.valueOf(scanner.nextDouble());
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println("Error registering the price. Default price (1.00) has been set.");
                            split[3] = String.valueOf(1.00);
                            System.out.println();
                        }
                        System.out.println();
                        String productUpdated = String.join("|", split);
                        products.remove(i);
                        products.add(i, productUpdated);
                        Files.write(path, products);
                    }
                }
            }
        }
    }

    public void deleteProduct(String id) throws IOException {
        String productLine = groceryController.searchProductById(id);
        if (productLine != null) {
            String[] split = productLine.split("\\|");
            if (groceryController.verifyAreYouSure(split[0], "delete")) {
                List<String> products = Files.readAllLines(path);
                products.remove(productLine);
                Files.write(path, products);
                System.out.println("Product with Id " + id + " has been deleted.");
                System.out.println();
            }
        }
    }
}
