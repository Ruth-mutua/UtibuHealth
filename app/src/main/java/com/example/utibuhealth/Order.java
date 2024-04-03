package com.example.utibuhealth;

public class Order {
    private  int orderID;
    private  int itemID;
    private String itemName;
    private String customerEmail;
    private int quantityOrdered;
    private double totalPrice;
    private String status;
    private String medicationType; // New field for medication type

    public Order(String itemName, String customerEmail, int quantityOrdered, double totalPrice, String status) {
        this.itemName = itemName;
        this.customerEmail = customerEmail;
        this.quantityOrdered = quantityOrdered;
        this.totalPrice = totalPrice;
        this.status = status;
    }


    // Getters for all fields
    public String getItemName() {
        return itemName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public double getPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    // Getter for medication type
    public String getMedicationType() {
        return medicationType;
    }

    // New constructor
    public Order(int orderID, int itemID, String customerEmail, int quantityOrdered, double totalPrice, String status) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.customerEmail = customerEmail;
        this.quantityOrdered = quantityOrdered;
        this.totalPrice = totalPrice;
        this.status = status;
    }

}
