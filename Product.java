package Milestone3;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product implements Comparable<Product>{
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productDescription")
    private String productDescription;
    @JsonProperty("productPrice")
    private double productPrice;
    @JsonProperty("productQuantity")
    private int productQuantity; // total amount of the product
    @JsonProperty("weaponHealth")
    private Health weaponHealth;
    @JsonProperty("armorDescription")
    private String armorDescription;
    @JsonProperty("armorName")
    private String armorName;
    @JsonProperty("armorHealth")
    private Health armorHealth;
    @JsonProperty("itemHealth")
    private int itemHealth;

    public Product (String productName, String productDescription, double productPrice, int productQuantity) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
    public Product(){

    }
    @JsonProperty("productName")

    public String getProductName() {
        return productName;
    }
    @JsonProperty("productName")

    public void setProductName(String productName) {
        this.productName = productName;
    }
    @JsonProperty("productDescription")

    public String getProductDescription() {
        return productDescription;

    }
    @JsonProperty("productPrice")

    public double getProductPrice() {
        return productPrice;
    }
    @JsonProperty("productQuantity")

    public int getProductQuantity() {
        return productQuantity;
    }
    @JsonProperty("productQuantity")

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "Name: " + getProductName() + " / Description: " + getProductDescription() +
                " / Price: " + getProductPrice() + " / Total Available: " + getProductQuantity();
    }

    @Override
    public int compareTo(Product name) {
        if (this.getProductName().compareToIgnoreCase(name.getProductName()) < 0)
            return -1;
        else if (this.getProductName().compareToIgnoreCase(name.getProductName()) > 0) {
            return 1;
        }
        else
            return 0;
    }

}