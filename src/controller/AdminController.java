package controller;

import entity.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private static final Product product = new Product();
    private static final Scanner scanner = new Scanner(System.in);
    GroceryController groceryController = new GroceryController();
    Path path = Paths.get("src/products.txt");

    public void registerProduct() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        int size = Files.readAllLines(path).size();

        if (size == 0) {
            product.setId(0);
        } else {
            List<String> products = Files.readAllLines(path);
            size--;
            String[] split = products.get(size).split("\\|");
            Integer valueOf = Integer.valueOf(split[0]);
            valueOf++;
            product.setId(valueOf);
        }

        System.out.println("Enter the name of the product.");
        product.setName(scanner.nextLine());

        System.out.println("Enter the quantity of the product.");
        product.setQuantity(scanner.nextInt());

        System.out.println("Enter the price of the product.");
        product.setPrice(scanner.nextDouble());

        Files.writeString(path, product.toString(), StandardOpenOption.APPEND);
    }

    public void updateProduct(int id) throws IOException {
        String productLine = groceryController.searchProductById(id);
        groceryController.verifyAreYouSure(id, "update");
        String[] split = productLine.split("\\|");
        List<String> products = Files.readAllLines(path);
        for (int i = 0; i <products.size(); i++) {
            if (products.get(i).equals(productLine)) {
                split[0] = String.valueOf(id);
                System.out.println("Enter the new Product Name:");
                split[1] = scanner.nextLine();
                System.out.println("Enter the new Product Quantity:");
                split[2] = String.valueOf(scanner.nextInt());
                System.out.println("Enter the new Product Price:");
                split[3] = String.valueOf(scanner.nextDouble());
                String productUpdated = String.join("|", split);
                products.get(i).replaceAll(products.get(i),productUpdated);
                Files.write(path, products);
            }
        }
    }

    public void deleteProduct(int id) throws IOException {
        String productLine = groceryController.searchProductById(id);
        String[] split = productLine.split("\\|");
        groceryController.verifyAreYouSure(Integer.parseInt(split[0]), "delete");
        List<String> products = Files.readAllLines(path);
        products.remove(productLine);
        Files.write(path, products);
    }
}
