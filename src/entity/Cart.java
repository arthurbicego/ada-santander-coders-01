package entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> productsCart = new ArrayList<>();

    public void addProduct(Product product){
        this.productsCart.add(product);
    }

    public List<Product> getProductsCart() {
        return this.productsCart;
    }

    public void setProductsCart(List<Product> productsCart) {
        this.productsCart = productsCart;
    }
}
