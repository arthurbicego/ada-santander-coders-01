package tech.ada.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> productsCart = new ArrayList<>();

    public void addProduct(Product product) {
        productsCart.add(product);
    }

    public List<Product> getProductsCart() {
        return this.productsCart;
    }

    public void setProductsCart(List<Product> products) {
        this.productsCart = products;
    }

}
