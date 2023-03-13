package Milestone3;

public class Armor extends Product{

    private String armorDescription;
    private String armorName;
    private Health armorHealth;

    public Armor(String armorName, String armorDescription, int quantity, int price) {
        super(armorName, armorDescription, quantity, price);
        this.armorName = armorName;
        this.armorDescription = armorDescription;
    }

    public Armor() {

    }
}