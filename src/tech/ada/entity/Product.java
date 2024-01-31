package tech.ada.entity;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private Map<String, Object> product = new HashMap<>();

    public String getId() {
        return (String) product.get("id");
    }

    public void setId(String id) {
        product.put("id", id);
    }

    public String getName() {
        return (String) product.get("name");
    }

    public void setName(String name) {
        product.put("name", name);
    }

    public Integer getQuantity() {
        return (Integer) product.get("quantity");
    }

    public void setQuantity(Integer quantity) {
        product.put("quantity", quantity);
    }

    public Double getPrice() {
        return (Double) product.get("price");
    }

    public void setPrice(Double price) {
        product.put("price", price);
    }

    @Override
    public String toString() {
        return product.get("id") + "|" + product.get("name") + "|" +
                product.get("quantity") + "|" + product.get("price") + "\n";
    }
}
