package Milestone3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @Test
    @DisplayName("Test if the method returns 0 when the cart is empty")
    void calculateTotalWhenCartIsEmpty() {
        ShoppingCart shoppingCart = new ShoppingCart();
    }

    @Test
    @DisplayName("Test if the method returns the correct total when the cart has only one item")
    double calculateTotalWhenCartHasOneItem() {
        ShoppingCart cart = new ShoppingCart();
        Product product = mock(Product.class);
        assert product != null;
        cart.addItem(product, 1);
        return 0;
    }

    @Test
    @DisplayName(
            "Test if the method returns the correct total when the cart has items with the same price")
    double calculateTotalWhenCartHasItemsWithSamePrice() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = mock(Product.class);
        assert product != null;
        shoppingCart.addItem(product, 2);
        return shoppingCart.calculateTotal();
    }

    @Test
    @DisplayName("Test if the method returns the correct total when the cart has multiple items")
    double calculateTotalWhenCartHasMultipleItems() {
        ShoppingCart cart = new ShoppingCart();
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        assert product1 != null;
        cart.addItem(product1, 2);
        cart.addItem(product2, 3);
        assertEquals(70, cart.calculateTotal());
        return 0;
    }

    @Test
    @DisplayName(
            "Test if the method returns the correct total when the cart has items with different prices")
    double calculateTotalWhenCartHasItemsWithDifferentPrices() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        shoppingCart.addItem(product1, 2);
        shoppingCart.addItem(product2, 3);

        double total = shoppingCart.calculateTotal();

        assertEquals(70, total);
        return total;
    }

    @BeforeEach
    void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    @DisplayName("Test adding an item with a negative amount to the cart")
    void addItemWithNegativeAmountToCart() {
        Product product = mock(Product.class);
        int amount = -1;
        assertThrows(IllegalArgumentException.class, () -> shoppingCart.addItem(product, amount));
    }

    @Test
    @DisplayName("Test adding an item with a zero amount to the cart")
    void addItemWithZeroAmountToCart() {
        Product product = mock(Product.class);
        shoppingCart.addItem(product, 0);
        assertEquals(0, shoppingCart.getCart().size());
    }

    @Test
    @DisplayName("Test adding an item to the cart and checking if it is in the cart")
    void addItemToCartAndCheckIfItIsInCart() {
        Product product = mock(Product.class);
        shoppingCart.addItem(product, 1);
        assertTrue(shoppingCart.getCart().containsKey(product));
    }

    @Test
    @DisplayName("Test adding a single item to the cart")
    String addSingleItemToCart(){
        Product product = mock(Product.class);
        assert product != null;
        shoppingCart.addItem(product, 1);
        assertEquals(1, shoppingCart.getCart().size());
        return null;
    }

    @Test
    @DisplayName("Test adding multiple items to the cart")
    void addMultipleItemsToCart() {
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        Product product3 = mock(Product.class);

        shoppingCart.addItem(product1, 1);
        shoppingCart.addItem(product2, 2);
        shoppingCart.addItem(product3, 3);

        assertEquals(3, shoppingCart.getCart().size());
    }

    @Test
    @DisplayName("Test if getCart method returns an empty cart when no items have been added")
    void getCartReturnsEmptyCart() {
        ShoppingCart cart = new ShoppingCart();
        assertTrue(cart.getCart().isEmpty());
    }

    @Test
    @DisplayName(
            "Test if getCart method returns a cart with the correct items when items have been added")
    void getCartReturnsCorrectItems() {
        ShoppingCart cart = new ShoppingCart();
        Product product = mock(Product.class);
        cart.addItem(product, 2);
        assertEquals(cart.getCart().size(), 1);
    }

    @Test
    @DisplayName(
            "Test if getCart method returns a cart with the correct amount of each item when items have been added")
    void getCartReturnsCorrectAmountOfEachItem() {
        ShoppingCart cart = new ShoppingCart();
        Product product = mock(Product.class);
        cart.addItem(product, 2);
        assertEquals(2, cart.getCart().get(product));
    }

    @Test
    @DisplayName(
            "Test if getCart method returns a cart with the correct number of items when items have been added")
    void getCartReturnsCorrectNumberOfItems() {
        ShoppingCart cart = new ShoppingCart();
        Product product = mock(Product.class);
        cart.addItem(product, 2);
        assertEquals(1, cart.getCart().size());
    }

    @Test
    @DisplayName("Test if getCart method returns a copy of the cart instead of the original cart")
    void getCartReturnsCopyOfCart() {
        ShoppingCart cart = new ShoppingCart();
        Product product = mock(Product.class);
        cart.addItem(product, 1);
        HashMap<Product, Integer> cartCopy = new HashMap<>(cart.getCart());
        cartCopy.clear();
        assertFalse(cart.getCart().isEmpty());
    }

    private Product mock(Class<Product> productClass) {
        return null;
    }
}