package Milestone3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static Milestone3.StoreFront.userInput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StoreFrontTest {

    @Test
    @DisplayName(
            "Test if connectToAdmin method prints the updated list when 'R' command is entered")
    void connectToAdminPrintsUpdatedList() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        StoreFront.connectToAdmin();
        assertTrue(outContent.toString().contains("Printing updated list..."));
    }

    @Test
    @DisplayName("Test if connectToAdmin method prompts the user to enter a command")
    void connectToAdminPromptsUserToEnterCommand() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        StoreFront.connectToAdmin();
        assertTrue(outContent.toString().contains("Enter a command..."));
    }

    @Test
    @DisplayName("Test if connectToAdmin method connects to the admin user")
    void connectToAdminConnectsToAdminUser() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        StoreFront.connectToAdmin();
        assertEquals(
                "Admin user is now connected to the StoreFront!\n"
                        + "\n"
                        + "Enter a command... \n"
                        + "U--> Update inventory \n"
                        + "R--> Return salable products \n"
                        + "X--> Exit update and go to StoreFront\n"
                        + "\n",
                outContent.toString());
    }


    @Test
    @DisplayName("Test if connectToAdmin method updates the inventory when 'U' command is entered")
    void connectToAdminUpdatesInventory() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        StoreFront.connectToAdmin();
        assertEquals(
                "Admin user is now connected to the StoreFront!\n"
                        + "\n"
                        + "Enter a command... \n"
                        + "U--> Update inventory \n"
                        + "R--> Return salable products \n"
                        + "X--> Exit update and go to StoreFront\n"
                        + "\n"
                        + "StoreFront items updated\n"
                        + "Enter 'X' to go to storefront\n",
                outContent.toString());
    }

    @Test
    @DisplayName("Test if connectToAdmin method returns to StoreFront when 'X' command is entered")
    InputStream connectToAdminReturnsToStoreFront() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        StoreFront.connectToAdmin();
        assertEquals(
                "Admin user is now connected to the StoreFront!\n"
                        + "\n"
                        + "Enter a command... \n"
                        + "U--> Update inventory \n"
                        + "R--> Return salable products \n"
                        + "X--> Exit update and go to StoreFront\n"
                        + "\n"
                        + "Invalid input.\n"
                        + "Enter 'X' to go to storefront\n",
                outContent.toString());
        return null;
    }


    @Test
    @DisplayName("Verify if the user can update the inventory")
    void verifyUserCanUpdateInventory() throws IOException {
        StoreFront.connectToAdmin();
        String commandInput = userInput.nextLine();
        assertEquals("U", commandInput);
    }

    @Test
    @DisplayName("Verify if the user can view the updated inventory")
    void verifyUserCanViewUpdatedInventory() {
        List<Product> myInventory = new ArrayList<Product>();
        Product p1 = new Armor("Zeus Armor", "Big Titanium Armor", 90, 25);
        Product p2 = new Armor("Kratos Armor", "Medium Silver Armor", 70, 55);
        Product p3 =
                new Weapon("Blades of Hades", "Two Swords attached to Retractable Chains", 40, 5);
        Product p4 = new Weapon("Death Spear", "Long Spear with Vibranium Tip", 30, 15);
        Product p5 = new Health(10, 500);
        myInventory.add(p1);
        myInventory.add(p2);
        myInventory.add(p3);
        myInventory.add(p4);
        myInventory.add(p5);
        Collections.sort(
                myInventory,
                new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        int result = o1.getProductName().compareTo(o2.getProductName());
                        if (result == 0) {
                            if (o1.getProductPrice() < o2.getProductPrice()) {
                                return -1;
                            } else if (o1.getProductPrice() > o2.getProductPrice()) {
                                return 1;
                            } else {
                                return 0;
                            }
                        } else return result;
                    }
                });

        Inventory inventory = new Inventory((ArrayList<Product>) myInventory);

        assertEquals(p1, inventory.verifyProduct("Zeus Armor"));
    }
    
}