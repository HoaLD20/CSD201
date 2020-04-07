/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author TrongDuyDao
 */
public class Product implements Serializable {
    
    private String code, name;
    private int quantity, saled;
    private double price;

    public Product() {
    }

    public Product(String code, String name, int quantity, int saled, double price) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.saled = saled;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSaled() {
        return saled;
    }

    public void setSaled(int saled) {
        this.saled = saled;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-10d%-10d%-10.2f", code,name,quantity,saled,price);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Product) {
            Product p = (Product)obj;
            return p.code.equalsIgnoreCase(this.code);
        }
        return false;
    }
    
    
    
}
