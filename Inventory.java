package Milestone3;

import java.util.ArrayList;
import java.util.List;

public class Inventory <T>{

    private List<T> inventory;

    public Inventory(ArrayList<T> inventory) {

        this.inventory = inventory;
    }

    public Inventory() {

    }

    public T verifyProduct(String itemName) {
        for(int i = 0; i < inventory.size(); i++) {
            if(itemName.equalsIgnoreCase(((Product)(inventory.get(i))).getProductName())) {
                return inventory.get(i);
            }
        }
        return null;
    }

}
