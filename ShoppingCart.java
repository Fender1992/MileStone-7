package Milestone3;

import java.util.HashMap;

public class ShoppingCart {

    private HashMap<Product, Integer> cart; // mapping b/w products and amount of product in your cart

    public ShoppingCart() {
        cart = new HashMap<Product, Integer>();
    }

    public void addItem(Product p, int amount) {
        cart.put(p, amount);
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public double calculateTotal() {
        double cartTotal = 0;
        for (Product p : cart.keySet()) {
            cartTotal += (p.getProductPrice() * cart.get(p));
        }
        return cartTotal;
    }
}
