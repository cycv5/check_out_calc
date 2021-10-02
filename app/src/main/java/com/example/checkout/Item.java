package com.example.checkout;

public class Item {
    private String name;
    private final double price;
    private final double quant;
    private final double discount;

    public Item(String name, double price, double quant, double discount) {
        this.name = name;
        this.price = price;
        this.quant = quant;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public double getQuant() {
        return quant;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalPrice(){
        return price * quant * ((100 - discount) / 100);
    }

}
