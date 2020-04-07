package util;

import entity.Product;
import java.util.List;

/**
 *
 * @author Le Duc Hoa
 */
class PriceBSTree {
     // A root of tree
    Node<Product> root;

    public PriceBSTree() {
        root = null;
    }

    public void insert(Product product) {
        root = insertRec(root, product);
    }

    private Node<Product> insertRec(Node<Product> root, Product product) {
        if (root == null) {
            root = new Node<>(product);
            return root;
        }
        if (product.getPrice() < root.info.getPrice()) {
            root.left = insertRec(root.left, product);
        } else if (product.getPrice() > root.info.getPrice()) {
            root.right = insertRec(root.right, product);
        }
        return root;
    }

    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.info.toString() + String.format("%-10s", this.getHeight(root)));
            inOrder(root.right);
        }
    }

    public void addTree(List<Node<Product>> list) {
        for (int i = 0; i < list.size(); i++) {
            insert(list.get(i).info);
        }
    }

    public int getHeight(Node<Product> root) {
        if (root == null) {
            return -1;
        } else {
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }
    }
}
