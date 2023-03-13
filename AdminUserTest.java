package Milestone3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdminUserTest {

    @Test
    @DisplayName("Test if the main method runs without throwing an exception")
    void mainMethodRunsWithoutException() {
        assertDoesNotThrow(
                () -> {
                    AdminUser.main(new String[]{});
                });
    }

    @Test
    @DisplayName("Test if the shoppingOperations method calculates the correct total price")
    void shoppingOperationsCalculatesCorrectTotalPrice() throws IOException {
        AdminUser adminUser = new AdminUser();
        adminUser.shoppingOperations();
        assertEquals(adminUser.calculateTotal(), 714.00);
    }

    @Test
    @DisplayName("Test if the Commands method connects to the server socket")
    void commandsConnectsToServerSocket() {
        AdminUser adminUser = new AdminUser();
        assertDoesNotThrow(() -> adminUser.Commands());
    }

    @Test
    @DisplayName("Test if the shoppingOperations method adds items to the shopping cart")
    void shoppingOperationsAddsItemsToCart() throws IOException {
        AdminUser adminUser = new AdminUser();
        adminUser.shoppingOperations();
        assertEquals(6, adminUser.getCart().getCart().size());
    }

    @Test
    @DisplayName(
            "Test if the shoppingOperations method throws an exception when an invalid item is entered")
    void shoppingOperationsThrowsExceptionOnInvalidItem() {
        AdminUser adminUser = new AdminUser();
        assertThrows(NullPointerException.class, () -> adminUser.shoppingOperations());
    }
}