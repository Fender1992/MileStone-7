package Milestone3;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Weapon extends Product {
    @JsonIgnore
    private Health weaponHealth;

    public Weapon(String productName, String productDescription, int Quantity, int itemPrice) {
        super(productName, productDescription, Quantity, itemPrice);
    }

    public Weapon() {

    }
}