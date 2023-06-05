package entity;

import java.util.List;

public class Cart {
    private List<Product> productsCart;

    public List<Product> getProductsCart() {
        return productsCart;
    }

    public void setProductsCart(List<Product> productsCart) {
        this.productsCart = productsCart;
    }
}
