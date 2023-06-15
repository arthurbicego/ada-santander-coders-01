package entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static List<Product> productsCart = new ArrayList<>();

    public void addProduct(Product product){
        productsCart.add(product);
    }

    public List<Product> getProductsCart() {
        return productsCart;
    }

    public static void setProductsCart(List<Product> products) {
        productsCart = products;
    }

}
