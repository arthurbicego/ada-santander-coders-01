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
    public static Cart cart = new Cart();
    private static final Scanner scanner = new Scanner(System.in);
    GroceryController groceryController = new GroceryController();
    GroceryView groceryView = new GroceryView();
    Path path = Paths.get("src/products.txt");

    public void buyProduct(String id) throws IOException {
        String productLine = groceryController.searchProductById(id);
        groceryController.verifyAreYouSure(id, "buy");
        System.out.println("Enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        groceryController.verifyQuantity(id, quantity);

        String[] split = productLine.split("\\|");
        List<String> products = Files.readAllLines(path);
        for (int i = 0; i <products.size(); i++) {
            if (products.get(i).equals(productLine)) {
                Product product = new Product();
                product.setId(split[0]);
                product.setName(split[1]);
                product.setQuantity(quantity);
                product.setPrice(Double.valueOf(split[3]));
                cart.addProduct(product);
                System.out.println("\nProduct added to shopping cart.\n");

                split[2] = String.valueOf(Integer.parseInt(split[2]) - quantity);
                String productUpdated = String.join("|", split);
                products.remove(i);
                products.add(i, productUpdated);
                Files.write(path, products);
            }
        }
    }

    public void removeProductShoppingCart(String id) throws IOException {
        verifyProductCart(id);
        groceryController.verifyAreYouSure(id, "remove");
        List<Product> products = cart.getProductsCart();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                Cart.setProductsCart(products);
            }
        }
    }

    public void checkout() throws IOException {
        System.out.println("Your shopping cart:\n");
        List<Product> productsCart = cart.getProductsCart();
        for (Product product : productsCart) {
            String showProduct = product.toString().replaceAll("\n","");
            groceryView.showProduct(showProduct);
        }
        List<Product> products = cart.getProductsCart();
        double checkoutValue = 0d;
        for (Product product : products) {
            checkoutValue = checkoutValue + (product.getQuantity() * product.getPrice());
        }
        System.out.printf("The total checkout amount is: $%.2f", checkoutValue);
        System.out.println();
        System.out.println();

    }

    public Object verifyProductCart(String id) throws IOException {
        List<Product> productsCart = cart.getProductsCart();
        for (Product product : productsCart) {
            if (id == product.getId()) {
                return null;
            }
        }
        System.out.printf("Product Id " + id + " not found in Shopping Cart.");
        System.out.println();
        groceryView.menuCustomer();
        return null;
    }
}
