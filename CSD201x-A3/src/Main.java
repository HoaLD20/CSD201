
import entity.Product;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TrongDuyDao
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        MyProduct myProduct = new MyProduct();
        
        while (true) {
            System.out.println("Product List");
            System.out.println("1.Insert a new product");
            System.out.println("2.In-order traverse");
            System.out.println("3.Breadth first traverse");
            System.out.println("4.Search by a product code");
            System.out.println("5.Delete by a product code");
            System.out.println("6.Simple balancing");
            System.out.println("7.Count number of products");
            System.out.println("8.Display custom product");
            System.out.println("9.Binary search custom");
            System.out.println("10.Sleepy");
            System.out.println("0. Exit");
            System.out.print("\tYour choice: ");

            int choice = Validation.checkInputLimit(0, 9);

            switch (choice) {
                case 1: 
                    myProduct.insert();
                    break;
                case 2:
                    myProduct.inOrder();
                    break;
                case 3:
                    myProduct.BFT();
                    break;
                case 4:
                    myProduct.search();
                    break;
                case 5:
                    myProduct.delete();
                    break;
                case 6:
                    myProduct.balance();
                    break;
                case 7:
                    myProduct.size();
                    break;
                case 8:
                    myProduct.customSearch();
                    break;
                case 9:
                    myProduct.findByPrice();
                    break;
                case 10:
                    break;
                default:
                    return;
            }
        }
    }
}
