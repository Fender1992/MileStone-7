package Milestone3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminUser extends StoreFront{
    public static <T> void main(String[] args) throws IOException {
        //Call commands method
        Commands();
        //Call shopping operations to Main
        shoppingOperations();


    }



    //Runs NIO to link Storefront
    public static void Commands() throws IOException {
        System.out.println("Connecting to Storefront...");
        //Connect port to new SS
        ServerSocket Admin = new ServerSocket(1234);
        //accept server request
        Socket client = Admin.accept();




    }
    //Runs shopping operations for updated items
    public static <T> void shoppingOperations() throws IOException {
        //updated items
        Product p6 = new Armor("Titan Armor", "Generates force-field", 300, 65);
        Product p7 = new Armor("Hunter Armor", "Increases agility", 99, 55);
        Product p8 = new Armor("Warlock Armor", "Increases mystical attacks" ,120, 45);

        Product p9 = new Weapon("WarHammer", "Earthquake attack", 200, 85);
        Product p10 = new Armor("CrossBow", "Shoot 100 arrows", 99, 95);
        Product p11 = new Armor("Staff", "Magnifies spells" ,120, 90);

        //Create new inventory for items
        List <T> newInventory = new ArrayList<T>();
        newInventory.add((T)p6);
        newInventory.add((T)p7);
        newInventory.add((T)p8);
        newInventory.add((T)p9);
        newInventory.add((T)p10);
        newInventory.add((T)p11);

        ObjectMapper objectMapper2 = new ObjectMapper();
        StringWriter writer = new StringWriter();
        //write products to the MyInventory file
        try {
            File file = new File("MyInventory2");
            objectMapper2.writeValue(file, newInventory);
            Scanner scanner = new Scanner(file);
            String json = scanner.nextLine();

        }catch(IOException e) {
            e.printStackTrace();
        }

        //Add items to new inventory object
        Inventory inventory = new Inventory<>((ArrayList<T>) newInventory);
        //instantiate new cart object
        ShoppingCart cart = new ShoppingCart();

        while(true) {
            System.out.println("Products available for purchase: \n");
            for (T p : newInventory) {
                //System.out.println(p);
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
        //Allocate bytes
        ByteBuffer buf = ByteBuffer.allocate(1048);

        while (buf.hasRemaining()) {
            System.out.print((char) buf.get());
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

    public double calculateTotal() {
        return 0;
    }

    public AdminUser getCart() {
        return null;
    }

    public int size() {
        return 0;
    }
}
