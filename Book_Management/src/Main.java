import entity.Book;

import java.util.Scanner;

/**
 * @author TrongDuyDao
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static int menu() {
        System.out.println("\nBook List");
        System.out.println("1. Input Book and add to the end");
        System.out.println("2. Display books");
        System.out.println("3. Search by code");
        System.out.println("4. Input Book and add to beginning");
        System.out.println("5. Add Book after position k");
        System.out.println("6. Delete Book at position k");
        System.out.println("7. Sort book by price");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
        int choice = Validates.checkInputLimit(0, 7);
        return choice;
    }

    public static void main(String[] args) {
        BookList bookList = new BookList();
        while (true) {
            int choice = menu();
            switch (choice) {
                case 1: {
                    bookList.addLast();
                    break;
                }
                case 2: {
                    bookList.list();
                    break;
                }
                case 3: {
                    bookList.search();
                    break;
                }
                case 4: {
                    bookList.addFirst();
                    break;
                }
                case 5: {
                    bookList.addAfter();
                    break;
                }
                case 6: {
                    bookList.deleteAt();
                    break;
                }
                case 7:{
                    bookList.sortByPrice();
                    break;
                }
                case 0: {
                    System.out.println("Thank you <3 !");
                    System.exit(0);
                }
            }
        }
    }
}

