package com.example.messe;

public class ItemInventory {
    private String name;
    private String quantity;
    private String type;
    private String count;

    public ItemInventory(String name, String quantity, String type) {
        this.name = name;
        this.quantity = quantity;
        this.type = type;
        this.count = "0";
    }

    public String getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
