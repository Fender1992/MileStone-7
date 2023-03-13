package Milestone3;


import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;


public class StoreFront {
    static Scanner userInput = new Scanner(System.in);

    public static <T> void main(String[] args) throws IOException {

        connectToAdmin();

        //Greet customers
        System.out.println("Welcome to our StoreFront\n");


        //instantiate new cart object
        ShoppingCart cart = new ShoppingCart();


        //create products for storefront
        Product p1 = new Armor("Zeus Armor", "Big Titanium Armor", 90, 25);
        Product p2 = new Armor("Kratos Armor", "Medium Silver Armor", 70, 55);
        Product p3 = new Weapon("Blades of Hades", "Two Swords attached to Retractable Chains", 40, 5);
        Product p4 = new Weapon("Death Spear", "Long Spear with Vibranium Tip", 30, 15);

        //initialize product health of items
        Product p5 = new Health(10, 500);

        //create generic array object then add items to the inventory
        //Inventory<Product> myInventory = new Inventory<Product>();
        List<T> myInventory = new ArrayList<T>();
        myInventory.add((T) p1);
        myInventory.add((T) p2);
        myInventory.add((T) p3);
        myInventory.add((T) p4);
        myInventory.add((T) p5);

        //Sort items alphabetically by productName if names are similar compare price
        Collections.sort(myInventory, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Product product1 = (Product) o1;
                Product product2 = (Product) o2;
                int result = product1.getProductName().compareTo(product2.getProductName());
                if(result == 0){
                    if(product1.getProductPrice() < product2.getProductPrice()){
                        return -1;
                    }else if (product1.getProductPrice() > product2.getProductPrice()){
                        return 1;
                    }else {
                        return 0;
                    }
                }else return result;
            }
        });

        //create object that writes out document
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        //write products to the MyInventory file
        try {
            File file = new File("MyInventory");
            objectMapper.writeValue(file, myInventory);
            Scanner scanner = new Scanner(file);
            String json = scanner.nextLine();
            System.out.println(json);

            //Product[] invent = objectMapper.readValue(json, Product[].class);
            //System.out.println(Arrays.toString(invent));
        }catch(IOException e) {
            e.printStackTrace();
        }


        //construct new inventory object to verify product name to the name of current item
        Inventory inventory = new Inventory((ArrayList<Product>) myInventory);

        //run while loop to check item totals, names and prices
        while(true) {
            System.out.println("Products available for puchase: \n");
            for (T p : myInventory) {
                System.out.println(p);
            }
            System.out.print("\nWhat is the name of the product you would you like to buy? ");
            String productName = userInput.nextLine();

            //verify that product and inventory is the product that the user selects.
            Product chosen = (Product)(inventory.verifyProduct(productName));
            //if user selects a product that is not in our inventory see print line message
            if(chosen == null) {
                System.out.println("\nPlease enter a valid item\n");
                continue;
            } else {
                System.out.print("\nHow many " + productName.toLowerCase() + "s are you buying? ");
                int chosenAmount = userInput.nextInt();
                userInput.nextLine();

                //if user selects a negative number print error message
                if (chosenAmount <= 0) {
                    System.out.println("Invalid quantity entered \n");

                    //if user enters amount greater than inventory print error message
                } else if (chosenAmount > chosen.getProductQuantity()) {
                    System.out.println("Not enough inventory. You can only buy " + chosen.getProductQuantity() + " " + productName + "(s)!");
                } else {
                    //if none of the above criteria is invalidated at items to cart
                    System.out.println("Added " + chosenAmount + " " + productName.toLowerCase() + "(s) to your shopping cart!\n");

                    //call Hashmap method to add products to our cart
                    cart.addItem(chosen, chosenAmount);

                    //Subtract chosen amount from the amount that is in our inventory.
                    chosen.setProductQuantity(chosen.getProductQuantity() - chosenAmount);

                    System.out.print("Are you ready to check out (Y/N)? ");

                    String ready = userInput.nextLine().trim();
                    System.out.println("");
                    //if they are ready to check out breakout of the code and display final shopping cart totals and amount
                    if(ready.equalsIgnoreCase("Y")) {
                        break;
                    } else {
                        //if user selects no return to the top of the while loop
                        continue;
                    }
                }
            }
        }
        //display final shopping cart
        System.out.println("\nYour final shopping cart: ");
        //go through keys in hashmap and add to cart
        for (Product p : cart.getCart().keySet()) {
            System.out.println(p.getProductName() + " (" + cart.getCart().get(p) + ")");
        }
        System.out.println("-----");
        //display card total in two decimal places
        System.out.println("TOTAL: $" + String.format("%.2f", cart.calculateTotal()));
    }
    public static void connectToAdmin() throws IOException {

        Socket storefrontSocket = new Socket("localhost",1234);
        System.out.println("Admin user is now connected to the StoreFront!");
        System.out.println("");

        System.out.println("Enter a command... \nU--> Update inventory \nR--> Return salable products \nX--> Exit update and go to StoreFront");
        System.out.println("");
        String commandInput = userInput.nextLine();
        InputStreamReader toAdmin = new InputStreamReader(storefrontSocket.getInputStream());

        while (!commandInput.equals("X")){
            if (commandInput.equals("U")){
                //Create new file
                RandomAccessFile toFile = new RandomAccessFile("MyInventory2", "rw");
                //Get the files over from admin user channel
                FileChannel toChannel = toFile.getChannel();
                ByteBuffer buf = ByteBuffer.allocate(1048);
                System.out.println("StoreFront items updated");
            } else if (commandInput.equals("R")) {
                System.out.println("Printing updated list...");
                RandomAccessFile toFile = new RandomAccessFile("MyInventory2", "rw");
                FileChannel toChannel = toFile.getChannel();
                ByteBuffer buf = ByteBuffer.allocate(1048);
                int bytesRead = toChannel.read(buf);
                //Prepare to write file into buf
                buf.flip();
                while(buf.hasRemaining()){
                    System.out.print((char) buf.get());
                }
            }else {
                //If one of the above not selected print invalid input
                System.out.println("Invalid input.");
            }
            System.out.println("Enter 'X' to go to storefront");
            commandInput = userInput.nextLine();
        }
        //Call admin user shopping operations
        AdminUser.shoppingOperations();
    }
}