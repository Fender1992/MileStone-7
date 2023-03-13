package Milestone3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    Product p1 = new Product("Weapon", "Blade", 1, 3);
    List<Product> testInv = new ArrayList<>();
    Inventory classInv = new Inventory();
    @Test
    void AddItemToList(){
        InventoryTest test = new InventoryTest();
        testInv.add(p1);
        assertTrue(testInv.contains(p1));
    }
    @Test
    void verifyProductIsProduct() {
    }
}