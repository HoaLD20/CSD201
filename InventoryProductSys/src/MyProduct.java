/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import util.MyBSTree;
import util.Node;

/**
 *
 * @author TrongDuyDao
 */
public class MyProduct {

    //a list of products
    MyBSTree tree;

    public MyProduct() throws IOException {

        tree = new MyBSTree();

    }

    //1.1 input and insert a new product to tree
    public void insert() {

        System.out.print("Enter code of product: ");
        String code = Validation.checkEmptyInputString("code");
        System.out.print("Enter name of product: ");
        String name = Validation.checkEmptyInputString("name");
        System.out.print("Enter quantity of product: ");
        int quantity = Validation.checkEmptyInt("quantity");
        System.out.print("Enter saled of product: ");
        int saled = Validation.checkEmptyInt("saled");
        System.out.print("Enter price of product: ");
        double price = Validation.checkEmptyDouble("price");
        tree.insert(new Product(code, name, quantity, saled, price));
        System.out.println("\n\tProduct has been saved\n");
    }

    //1.2 in-order traverse
    public void inOrder() {
        tree.inOrder();
    }

    //1.3 BFT a tree
    public void BFT() {
        tree.BFT();
    }

    //1.4 search a product by product code
    public void search() {
        Node<Product> product = null;
        System.out.print("Enter code for searching: ");
        String codeSearch = Validation.checkEmptyInputString("code");
        product = tree.search(codeSearch);
        if (product != null) {
            System.out.println("Information of product code " + product.info.getCode());
            tree.visit(product);
        } else {
            System.err.println("The product code " + codeSearch + " is not exist!");
        }
    }

    //1.5 delete a product by product code
    public void delete() {
        System.out.print("Enter code: ");
        String codeDelete = Validation.checkEmptyInputString("code");
        tree.delete(codeDelete);

    }

    //1.6 simply balancing a tree
    public void balance() {
        tree.balance();
    }

    //1.7 count the number of products in the tree
    public void size() {
        System.out.println("\n\tNumber of products: " + tree.count() + "\n");
    }

    public void findByPrice() {
        System.out.print("Enter price you want to find: ");
        double priceSearch = Validation.checkEmptyDouble("price");
        tree.findByPrice(priceSearch);

    }
    //custom search
        public void customSearch() {
        Node<Product> product = null;
        System.out.print("Enter price you want to find: ");
        double price = Validation.checkEmptyDouble("price");
        
       
        tree.customSearch(price);
        if (product != null) {
            System.out.println("Information of product code " + product.info.getCode());
            tree.visit(product);
        }   
    }
}
