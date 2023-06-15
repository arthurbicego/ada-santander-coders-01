import entity.Cart;
import view.GroceryView;

import java.io.IOException;

public class GroceryMain {
    public static void main(String[] args) throws IOException {
        GroceryView view = new GroceryView();
        System.out.println();
        view.menuMain();
    }
}
