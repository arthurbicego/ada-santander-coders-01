# Ada - Santander Coders: Unit 01

### Grocery Store Project - CRUD

This project creates a grocery store program that allows users to create, edit, delete, search for products, and make
purchases. Product data is saved in a .txt file.

- [Statement](#statement)
- [Concepts](#concepts)

[See the project in Development Envir[README.md](README.md)onment](https://github.dev/arthurbicego/ada01)

---

### Statement

Create a grocery store program with the following options below. For each operation a file must be manipulated to save
product data, so that if you exit the program, the information will persist.

Note: You will only need to save the product data in a file.

- Create product containing the information product name, quantity and price.
- Edit product, which takes the new information and updates the product chosen by the identifier. (To do this list the
  products with an identifier being the position of the product).
- Exclude product, which takes the position of the product and deletes it. (For this, list the products with an
  identifier being the position of the product)
- Product search. Get the name or part of it, filter all products that contain the name and show the filtered list.
- Purchase of products, where the user can choose products and quantities as he wants, as soon as he chooses to
  finalize, show everything he bought, the prices and the total. When he chooses the product and quantity, check if the
  product has that quantity, if not, inform the user that it does not contain the quantity of this product in stock.
  Once the user confirms the purchase, deduct the quantities of the selected products.
- An exit option that closes the program if the user chooses to.

---

### Some Concepts

<details>
  <summary>Casting</summary>

  ```java
split[2] = String.valueOf(Integer.parseInt(split[2]) - quantity);
  ```
</details>
<details>
  <summary>Input</summary>

  ```java
String id = scanner.nextLine();
  ```
</details>
<details>
  <summary>Output</summary>

  ```java
System.out.println("Are you sure you want to " + method + " Product " + id + "?");
  ```

  ```java
System.out.printf("The total checkout amount is: $%.2f", checkoutValue);
System.out.println();
  ```
</details>

<details>
  <summary>Array and String</summary>

  ```java
String[] split = productLine.split("\\|");
  ```

  ```java
split[1].toUpperCase().contains(name.toUpperCase())
  ```

  ```java
Objects.equals(split[0], id)
  ```
</details>
<details>
  <summary>Logical Operator</summary>

  ```java
productLine != null
  ```
</details>
<details>
  <summary>Selection Statement</summary>

  ```java
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
  ```
</details>
<details>
  <summary>Enhanced Switch</summary>

  ```java
switch (choice) {
    case "1" -> {
        groceryController.listProducts();
    }
  ```
</details>
<details>
  <summary>For, Enhanced For, ForEach</summary>

  ```java
for (int i = 0; i < cartProducts.size(); i++) {
    if (Objects.equals(cartProducts.get(i).getId(), id)) {
        cartProducts.remove(i);
        cart.setProductsCart(cartProducts);
    }
}
  ```
  ```java
for (Product product : products) {
    checkoutValue = checkoutValue + (product.getQuantity() * product.getPrice());
}
  ```
  ```java
products.forEach(product -> GroceryView.showProduct(product))
  ```
</details>
<details>
  <summary>Exceptions</summary>

  ```java
try {
    quantity = scanner.nextInt();
    scanner.nextLine();
} catch (Exception e) {
    System.out.println();
    System.out.println("Error registering the quantity. Default quantity (1) has been set.");
    quantity = 1;
}
  ```
</details>
<details>
  <summary>File Handling</summary>

  ```java
Path path = Paths.get("src/tech/ada/products.txt")
  ```
  ```java
Files.readAllLines(path);
  ```
  ```java
Files.write(path, products);
  ```
</details>