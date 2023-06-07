package controller;

import entity.Cart;
import entity.Product;
import view.GroceryView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private static final Product product = new Product();
    private static final Cart cart = new Cart();
    private static final Scanner scanner = new Scanner(System.in);
    GroceryController groceryController = new GroceryController();
    GroceryView groceryView = new GroceryView();
    Path path = Paths.get("src/products.txt");

    public void buyProduct(int id) throws IOException {
        String productLine = groceryController.searchProductById(id);
        groceryController.verifyAreYouSure(id, "buy");
        System.out.println("Enter the quantity: ");
        int quantity = scanner.nextInt();
        groceryController.verifyQuantity(id, quantity);

        String[] split = productLine.split("\\|");
        List<String> products = Files.readAllLines(path);
        for (int i = 0; i <products.size(); i++) {
            if (products.get(i).equals(productLine)) {
                product.setId(Integer.valueOf(split[0]));
                product.setName(split[1]);
                product.setQuantity(quantity);
                product.setPrice(Double.valueOf(split[3]));
                cart.addProduct(product);
                System.out.println("Product added to shopping cart.");

                split[2] = String.valueOf(Integer.parseInt(split[2]) - quantity);
                String productUpdated = String.join("|", split);
                products.get(i).replaceAll(products.get(i),productUpdated);
                Files.write(path, products);
            }
        }
    }

    public void removeProductShoppingCart() throws IOException {
        System.out.println("Your shopping cart:");
        List<Product> products = cart.getProductsCart();
        for (Product product : products) {
            String productLine = product.toString();
            groceryView.showProduct(productLine);
        }
        System.out.println("Enter the Id of product you want to remove from Shopping Cart.");
        int id = scanner.nextInt();
        groceryController.verifyIdFound(id);
        groceryController.verifyAreYouSure(id, "remove");

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                cart.setProductsCart(products);
            }
        }
    }

    public void checkout() {
        System.out.println("Your shopping cart:");
        List<Product> products = cart.getProductsCart();
        double checkoutValue = 0d;
        for (Product product : products) {
            String productLine = product.toString();
            groceryView.showProduct(productLine);
            checkoutValue = checkoutValue + (product.getQuantity() * product.getPrice());
        }
        System.out.printf("\nThe total checkout amount is: $%.2f\n", checkoutValue);
    }
}
