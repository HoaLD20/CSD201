
package util;

import entity.Book;

/**
 *
 * @author TrongDuyDao
 */
public class MyList {
    
    Node<Book> head, tail, sort;

    /**
     * ctor
     */
    public MyList() {
        head = tail = sort = null;
    }

    /**
     * check if the list is empty or not
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * add a new Book to the end of list
     * @param b
     */
    public void addLast(Book b) {
        Node<Book> node = new Node<>(b);
        if (head == null){
            head = node;
            return;
        }
        node.next = null;
        tail = head;
        while (tail.next != null){
            tail = tail.next;
        }
        tail.next = node;
        return;
    }

    /**
     * add a new Book to the begining of list
     * @param b
     */
    public void addFirst(Book b) {
        Node<Book> node = new Node<>(b);
        node.next = this.head;
        this.head = node;
    }

    /**
     * output information of all books in the list
     */
    public void traverse() {
        Node<Book> node = head;
        System.out.printf("%-10s%-20s%-10s%-10s%-10s%-10s\n", "Code", "Title", "Quantity", "Lended", "Price", "Value");
        while(node != null){
            System.out.println(node.info.toString());
            node = node.next;
        }
    }

    /**
     * return number of nodes/elements in the list
     * @return
     */
    public int size() {
        Node<Book> node = head;
        int count = 0;
        while (node != null){
            count++;
            node = node.next;
        }
        return count;
    }

    /**
     * return a Node at position k, starting position is 0
     * @param k
     * @return
     */
    public Node<Book> getNode(int k) {
        Node<Book> node = head;
        int start = 0;
        while (node != null){
            if (start == k){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * add a new book after a position k
     * @param b
     * @param k
     */
    public void addAfter(Book b, int k) {
        Node<Book> node = this.getNode(k);
        if (node == null){
            System.err.println("Something went wrong!!!");
            return;
        }
        else {
            Node<Book> node2 = new Node<>(b);
            node2.next = node.next;
            node.next = node2;
            System.out.println("\nA new book has been added after position " + k);
        }
    }

    /**
     * delete a book at position k
     * @param k
     */
    public void deleteAt(int k) {
        if (head == null){
            System.err.println("List is empty ....");//ask user wanna try again or not?
            return;
        }
        else{
            Node<Book> temp = head;
            if (k == 0){
                head = temp.next;
                System.err.println("Delete success!");
                return;
            }
            else {
                for (int i = 0; temp != null && i < k - 1; i++) {
                    temp = temp.next;
                }
                if (temp == null || temp.next == null) {
                    System.err.println("Something wrong with position !!!");
                    return;
                }
                else {
                    Node<Book> node = temp.next.next;
                    System.err.println("Delete success!");
                    temp.next = node;
                }
            }
        }

    }

    /**
     * search a Node by a given book code
     * @param bCode
     * @return
     */
    public Node<Book> search(String bCode) {
        Node<Book> node = head;
        while (node != null){
            if (node.info.getbCode().equals(bCode)){
                return node;
            }
            node = node.next;
        }
        return null;


    }

    /**
     * check code exist in linked list
     * if code duplicate -> false >< -> true
     * @param bCode
     * @return
     */
    public boolean checkCodeExist(String bCode) {
        Node<Book> node = head;
        while (node != null) {
            if (node.info.getbCode().equalsIgnoreCase(bCode)) {
                return false;
            }
            node = node.next;
        }
        return true;
    }

    /**
     *
     */
    public void sort(){
        Node<Book> temp = this.head;
        while (temp != null){
            Node<Book> next = temp.next;
            quicksort(temp);
            temp = next;
        }

        head = sort;

    }

    /**
     *
     * @param sortNode
     */
    private void quicksort(Node<Book> sortNode){
        if (sort == null || sort.info.getPrice() >= sortNode.info.getPrice()) {
            sortNode.next = sort;
            sort = sortNode;
        } else {
            Node<Book> current = sort;
            while (current.next != null && current.next.info.getPrice() < sortNode.info.getPrice()) {
                current = current.next;
            }
            sortNode.next = current.next;
            current.next = sortNode;
        }
    }
    
}
