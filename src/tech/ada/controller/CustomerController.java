package tech.ada.controller;

import tech.ada.entity.Cart;
import tech.ada.entity.Product;
import tech.ada.view.GroceryView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CustomerController {
    private final Cart cart = new Cart();
    private final Scanner scanner = new Scanner(System.in);
    private final GroceryController groceryController = new GroceryController();
    private final Path path = Paths.get("src/tech/ada/products.txt");

    public void buyProduct(String id) throws IOException {
        int quantity = 1;
        String productLine = groceryController.searchProductById(id);
        if (productLine != null) {
            if (groceryController.verifyAreYouSure(id, "buy")) {
                System.out.println("Enter the quantity: ");
                try {
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println();
                    System.out.println("Error registering the quantity. Default quantity (1) has been set.");
                    quantity = 1;
                }

                if (groceryController.verifyQuantity(id, quantity)) {

                    String[] split = productLine.split("\\|");
                    List<String> products = Files.readAllLines(path);

                    boolean isProductInCart = false;

                    for (int i = 0; i < cart.getProductsCart().size(); i++) {
                        if (Objects.equals(split[1], cart.getProductsCart().get(i).getName())) {
                            cart.getProductsCart().get(i).setQuantity(cart.getProductsCart().get(i).getQuantity() + quantity);
                            isProductInCart = true;
                            for (int j = 0; j < products.size(); j++) {
                                if (products.get(j).equals(productLine)) {
                                    split[2] = String.valueOf(Integer.parseInt(split[2]) - quantity);
                                    String productUpdated = String.join("|", split);
                                    products.remove(j);
                                    products.add(j, productUpdated);
                                    Files.write(path, products);
                                }
                            }
                        }
                    }
                    if (!isProductInCart) {
                        for (int j = 0; j < products.size(); j++) {
                            if (products.get(j).equals(productLine)) {
                                Product product = new Product();
                                product.setId(split[0]);
                                product.setName(split[1]);
                                product.setQuantity(quantity);
                                product.setPrice(Double.valueOf(split[3]));
                                cart.addProduct(product);
                                System.out.println("\nProduct added to shopping cart.\n");

                                split[2] = String.valueOf(Integer.parseInt(split[2]) - quantity);
                                String productUpdated = String.join("|", split);
                                products.remove(j);
                                products.add(j, productUpdated);
                                Files.write(path, products);
                            }
                        }
                    }
                }
            }
        }
    }

    public void removeProductShoppingCart(String id) throws IOException {
        if (verifyProductCart(id)) {
            if (groceryController.verifyAreYouSure(id, "remove")) {
                String productLine = groceryController.searchProductById(id);
                String[] split = productLine.split("\\|");

                List<String> stockProducts = Files.readAllLines(path);
                for (int i = 0; i < stockProducts.size(); i++) {
                    if (stockProducts.get(i).equals(productLine)) {
                        // Product Quantity
                        split[2] = String.valueOf(Integer.parseInt(split[2]) + addStockQuantity(split[1]));
                        String productUpdated = String.join("|", split);
                        stockProducts.remove(i);
                        stockProducts.add(i, productUpdated);
                        Files.write(path, stockProducts);
                    }
                }
                List<Product> cartProducts = cart.getProductsCart();
                for (int i = 0; i < cartProducts.size(); i++) {
                    if (Objects.equals(cartProducts.get(i).getId(), id)) {
                        cartProducts.remove(i);
                        cart.setProductsCart(cartProducts);
                    }
                }
            }
        }
    }

    public Integer addStockQuantity(String productName) {
        for (Product product : cart.getProductsCart()) {
            if (Objects.equals(product.getName(), productName)) {
                return product.getQuantity();
            }
        }
        return null;
    }

    public Boolean checkout() {
        showShoppingCart();
        System.out.println("Do you want to complete your purchase?");
        System.out.println("1. Yes.");
        System.out.println("2. No! Go back to menu.");
        String option = scanner.nextLine();
        System.out.println();
        if (!Objects.equals(option, "1")) {
            return false;
        } else {
            System.out.println("Thank you for shopping with us!");
            System.out.println();
            return true;
        }
    }

    public void showShoppingCart() {
        System.out.println("Your shopping cart:\n");
        List<Product> productsCart = cart.getProductsCart();
        displayProducts(productsCart,0);
//        for (Product product : productsCart) {
//            String showProduct = product.toString().replaceAll("\n", "");
//            GroceryView.showProduct(showProduct);
//        }
        double checkoutValue = 0d;
        for (Product product : productsCart) {
            checkoutValue = checkoutValue + (product.getQuantity() * product.getPrice());
        }
        System.out.printf("The total checkout amount is: $%.2f", checkoutValue);
        System.out.println();
        System.out.println();
    }

    public Boolean verifyProductCart(String id) throws IOException {
        List<Product> productsCart = cart.getProductsCart();
        for (Product product : productsCart) {
            if (Objects.equals(id, product.getId())) {
                return true;
            }
        }
        System.out.printf("Product Id " + id + " not found in Shopping Cart.");
        System.out.println();
        System.out.println();
        return false;
    }

    public void displayProducts (List<Product> productsCart, int index) {
        if (index < productsCart.size()) {
            Product product = productsCart.get(index);
            String showProduct = product.toString().replaceAll("\n", "");
            GroceryView.showProduct(showProduct);
            displayProducts(productsCart, index + 1);
        }
    }

}
