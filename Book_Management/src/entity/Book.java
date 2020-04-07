
package entity;

import java.io.Serializable;

/**
 *
 * @author TrongDuyDao
 */
public class Book implements Serializable {
    
    private String bCode, title;
    private int quantity, lended;
    private double price;

    public Book() {
    }

    public Book(String bCode, String title, int quantity, int lended, double price) {
        this.bCode = bCode;
        this.title = title;
        this.quantity = quantity;
        this.lended = lended;
        this.price = price;
    }

    public String getbCode() {
        return bCode;
    }

    public void setbCode(String bCode) {
        this.bCode = bCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLended() {
        return lended;
    }

    public void setLended(int lended) {
        this.lended = lended;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-10d%-10d%-10.1f%-10.2f", bCode,title,
                quantity,lended,price,quantity * price);
    }
    
    
    
}
