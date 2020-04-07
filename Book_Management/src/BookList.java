


import entity.Book;
import util.MyList;
import util.Node;

/**
 * @author TrongDuyDao
 */
public class BookList {


    private String bcode;
    private String title;
    private int quantity;
    private int lended;
    private int position;
    private double price;


    //a list of book
    private MyList books;

    public MyList getBooks() {
        return books;
    }

    public BookList() {
        if (books == null) {
            books = new MyList();
        }
    }

    //1.0 accept information of a Book
    private Book getBook() {
        return new Book(bcode, title, quantity, lended, price);
    }

    /**
     * 1.1 accept and add a new Book to the end of book list
     */
    public void addLast() {
//        boolean validate = true;
//        bcode = title = "";
//        price = quantity = lended = 0;
        while (true) {
            System.out.print("Enter code: ");
            bcode = Validates.checkEmptyInputString("code");
            if (books.checkCodeExist(bcode)){
                break;
            }
            else{
                System.out.println("Book code must be unique");
            }
        }

        System.out.print("Enter title: ");
        title = Validates.checkEmptyInputString("title");
        System.out.print("Enter quantity: ");
        quantity = Validates.checkEmptyInt("quantity");
        System.out.print("Enter lender: ");
        lended = Validates.checkLended();
        System.out.print("Enter price: ");
        price = Validates.checkEmptyDouble("price");

        books.addLast(this.getBook());
    }

    /**
     * 1.2 output information of book list
     */
    public void list() {
       if (books.isEmpty()){
           System.err.println("List is empty ....");
       }
       else {
           books.traverse();
       }
    }

    /**
     * 1.3 search book by book code
     */
    public void search() {
        System.out.print("Enter code book for searching: ");
        bcode = Validates.checkEmptyInputString("code");
        Node<Book>  book = books.search(bcode);
        if (book == null){
            System.err.println(bcode + " is not exist ....");
        }
        else{
            System.out.println("Information of book code " + bcode);
//            System.out.printf("%-10s%-20s%-10s%-10s%-10s%-10s\n", "Code", "Title", "Quantity", "Lended", "Price", "Value");
            System.out.println(book.info.toString());
        }
    }

    /**
     * 1.4 accept and add a new Book to the begining of book list
     */
    public void addFirst() {

        while (true) {
            System.out.print("Enter code: ");
            bcode = Validates.checkEmptyInputString("code");
            if (books.checkCodeExist(bcode)){
                break;
            }
            else{
                System.out.println("Book code must be unique");
            }

        }

        System.out.print("Enter title: ");
        title = Validates.checkEmptyInputString("title");
        System.out.print("Enter quantity: ");
        quantity = Validates.checkEmptyInt("quantity");
        System.out.print("Enter lender: ");
        lended = Validates.checkLended();
        System.out.print("Enter price: ");
        price = Validates.checkEmptyDouble("price");

        books.addFirst(this.getBook());
    }

    /**
     * 1.5 Add a new Book after a position k
     */
    public void addAfter() {
        while (true) {
            System.out.print("Enter code: ");
            bcode = Validates.checkEmptyInputString("code");
            if (books.checkCodeExist(bcode)){
                break;
            }
            else {
                System.out.println("Book code must be unique");
            }
        }


        System.out.print("Enter title: ");
        title = Validates.checkEmptyInputString("title");
        System.out.print("Enter quantity: ");
        quantity = Validates.checkEmptyInt("quantity");
        System.out.print("Enter lender: ");
        lended = Validates.checkLended();
        System.out.print("Enter price: ");
        price = Validates.checkEmptyDouble("price");

        System.out.print("Enter adding position: ");
        position = Validates.checkEmptyInt("position");

        books.addAfter(this.getBook(), position);
    }

    /**
     * 1.6 Delete a Book at position k
     */
    public void deleteAt() {
        System.out.print("Enter position to delete: ");
        position = Validates.checkEmptyInt("position");
        books.deleteAt(position);
    }
    public void sortByPrice(){
        books.sort();
        books.traverse();
    }
}
