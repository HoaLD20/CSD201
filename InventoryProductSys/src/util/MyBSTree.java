/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author TrongDuyDao
 */
public class MyBSTree {

    //a root of tree
    Node<Product> root;

    public MyBSTree() {
        root = null;
    }

    //visit a node of a tree -> output information of visited node
    public void visit(Node<Product> p) {
        System.out.println(p.info.toString());
    }

    //return true if tree is empty otherwise return false
    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    //inorder a tree
    public void inOrder() {
        if (root == null) {
            System.err.println("List is empty...");
            return;
        }
        Stack<Node<Product>> stack = new Stack<Node<Product>>();
        Node<Product> curr = root;

        // Traverse the tree
        System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s", "Code", "Name", "Quantity", "Saled", "Price"));
        while (curr != null || stack.size() > 0) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.println(curr.info.toString());
            curr = curr.right;
        }

    }

    //count number of products
    public int count() {
        return size(root);
    }

    public int size(Node<Product> root) {
        if (root == null) {
            return 0;
        } else {
            return size(root.left) + 1 + size(root.right);
        }
    }

    //breadth-first traverse a tree
    public void BFT() {
        if (root == null) {
            System.err.println("Products list is empty...");
            return;
        }
        MyQueue queue = new MyQueue();
        this.balance();
        queue.enqueue(root);
        System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s", "Code", "Name", "Quantity", "Saled", "Price"));
        while (!queue.isEmpty()) {
            Node<Product> tmp = (Node<Product>) queue.dequeue();
            System.out.println(tmp.info.toString());

            /*Enqueue left child */
            if (tmp.left != null) {
                queue.enqueue(tmp.left);
            }

            /*Enqueue right child */
            if (tmp.right != null) {
                queue.enqueue(tmp.right);
            }
        }
    }

    //insert a new Product to a tree
    public void insert(Product product) {
        if (root == null) {
            root = new Node<>(product);
            return;
        }
        Node<Product> f, p;
        p = root;
        f = null;
        while (p != null) {
            if (p.info.getCode().equalsIgnoreCase(product.getCode())) {
                System.err.println("The product code " + product.getCode() + " already exists!");
                return;
            }
            f = p;
            if (product.getCode().compareToIgnoreCase(p.info.getCode()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (product.getCode().compareToIgnoreCase(f.info.getCode()) < 0) {
            f.left = new Node(product);
        } else {
            f.right = new Node(product);
        }
    }

    //balance a tree
    //step 1: traverse inorder tree and copy all item on tree to an arraylist
    //step 2: insert all items of list to a tree
    private void buildArray(List<Node<Product>> list, Node<Product> p) {
        if (p == null) {
            return;
        }
        buildArray(list, p.left);
        list.add(p);
        buildArray(list, p.right);
    }

    //step 2:
    private void balance(List<Node<Product>> list, int f, int l) {
        if (f > l) {
            return;
        }
        int mid = (f + l) / 2;
        Node<Product> p = list.get(mid);
        insert(p.info);
        balance(list, f, mid - 1);
        balance(list, mid + 1, l);
    }

    public void balance() {
        List<Node<Product>> list = new ArrayList<>();
        buildArray(list, root);
        MyBSTree tree = new MyBSTree();
        tree.balance(list, 0, list.size() - 1);
        root = tree.root;
    }

    //search a Node of tree by product code
    //return null if given code does not exists
    public Node<Product> search(String code) {
        if (isEmpty()) {
            return null;
        }
        Node<Product> product = root;
        while (product != null) {
            if (product.info.getCode().equalsIgnoreCase(code)) {
                break;
            }
            if (product.info.getCode().compareToIgnoreCase(code) < 0) {
                product = product.right;
            } else {
                product = product.left;
            }
        }
        return product;

    }

    //delete a node by a given product code
    public void delete(String code) {
        if (search(code) != null) {
            int beforeDelete = this.size(root);
            root = process(root, code);
            int afterDelete = this.size(root);
            if (beforeDelete == (afterDelete + 1)) {
                System.out.println("The product " + code + " has been deleted!");
                this.inOrder();
            }
        } else {
            System.err.println("Product " + code + " is not exist!");
        }

    }

    // Delete a node and insert a new node
    private Node<Product> process(Node<Product> root, String code) {
        if (root == null) {
            System.err.println("Llist is empty...");
            return root;
        }
        if (code.compareToIgnoreCase(root.info.getCode()) < 0) {
            root.left = process(root.left, code);
        } else if (code.compareToIgnoreCase(root.info.getCode()) > 0) {
            root.right = process(root.right, code);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.info.setCode(getMinCode(root.right));

            // Delete the inorder successor
            root.right = process(root.right, root.info.getCode());
        }
        return root;
    }

    // Find smallest child of subtree
    public String getMinCode(Node<Product> node) {
        String code = node.info.getCode();
        while (node.left != null) {
            code = node.left.info.getCode();
            node = node.left;
        }
        return code;
    }

    public void findByPrice(double price) {
        List<Node<Product>> list = new ArrayList<>();
        buildArray(list, root);
        if (list == null) {
            System.err.println("List is empty...");
            return;
        }
        List<Node<Product>> tmp = new ArrayList<>();
        selectionSort(list);
        for (Node<Product> p : list) {
            if (p.info.getPrice() >= price) {
                tmp.add(p);
            }
        }
        if (tmp != null) {
            PriceBSTree priceTree = new PriceBSTree();
            priceTree.addTree(tmp);
            System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s%-10s", "Code", "Name", "Quantity", "Saled", "Price", "Height"));
            priceTree.inOrder(priceTree.root);
        }
    }

    private void selectionSort(List<Node<Product>> list) {
        int min;
        for (int i = 0; i < list.size() - 1; i++) {
            min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).info.getPrice() < list.get(min).info.getPrice()) {
                    min = j;
                }
            }
            if (min != i) {
                Node<Product> tmp = list.get(i);
                list.set(i, list.get(min));
                list.set(min, tmp);
            }
        }
    }

    public void customSearch(double price) {
        List<Node<Product>> list = new ArrayList<>();
        buildArray(list, root);
        if (list == null) {
            System.err.println("List is empty...");
            return;
        }
        List<Node<Product>> tmp = new ArrayList<>();
        for (Node<Product> p : list) {
            if (p.info.getPrice() >= price) {
                tmp.add(p);
            }
        }
        if (tmp != null) {
            PriceBSTree priceTree = new PriceBSTree();
            priceTree.addTree(tmp);
            System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s%-10s", "Code", "Name", "Quantity", "Saled", "Price", "Height"));
            priceTree.inOrder(priceTree.root);
        }

    }

}
