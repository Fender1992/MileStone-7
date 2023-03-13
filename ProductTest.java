package Milestone3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductTest {

    @Test
    @DisplayName("Test if getProductName returns a non-null value")
    void getProductNameReturnsNonNull() {
        Product product = new Product();
        assertNotNull(product.getProductName());
    }

    @Test
    @DisplayName("Test if setProductName sets the correct product name")
    void setProductNameSetsCorrectName() {
        Product product = new Product();
        product.setProductName("Sword");
        assertEquals("Sword", product.getProductName());
    }

    @Test
    @DisplayName("Test if getProductName returns a string")
    void getProductNameReturnsString() {
        Product product = new Product();
        assertEquals(String.class, product.getProductName().getClass());
    }

    @Test
    @DisplayName("Test if setProductName sets a non-null value")
    void setProductNameSetsNonNull() {
        Product product = new Product();
        product.setProductName("Sword");
        assertNotNull(product.getProductName());
    }

    @Test
    @DisplayName("Test if getProductName returns the correct product name")
    void getProductNameReturnsCorrectName() {
        Product product = new Product("Sword", "A sharp sword", 10.00, 5);
        assertEquals("Sword", product.getProductName());
    }
}