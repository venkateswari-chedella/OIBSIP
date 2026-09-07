import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;
    boolean issued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }
}

public class DigitalLibrary {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {

        // Sample books
        books.add(new Book(1, "Java Programming", "James Gosling"));
        books.add(new Book(2, "Python Basics", "Guido van Rossum"));
        books.add(new Book(3, "Data Structures", "Robert Lafore"));

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("       DIGITAL LIBRARY");
            System.out.println("================================");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    displayBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    issueBook();
                    break;

                case 5:
                    returnBook();
                    break;

                case 6:
                    System.out.println("Thank you for using Digital Library!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }

    // Add Book
    static void addBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));

        System.out.println("Book added successfully!");
    }

    // Display Books
    static void displayBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nAvailable Books:");

        for (Book book : books) {

            System.out.println("--------------------------------");
            System.out.println("Book ID     : " + book.id);
            System.out.println("Title       : " + book.title);
            System.out.println("Author      : " + book.author);
            System.out.println("Status      : "
                    + (book.issued ? "Issued" : "Available"));
        }
    }

    // Search Book
    static void searchBook() {

        System.out.print("Enter book title to search: ");
        String search = sc.nextLine();

        boolean found = false;

        for (Book book : books) {

            if (book.title.toLowerCase().contains(search.toLowerCase())) {

                System.out.println("\nBook Found!");
                System.out.println("Book ID : " + book.id);
                System.out.println("Title   : " + book.title);
                System.out.println("Author  : " + book.author);
                System.out.println("Status  : "
                        + (book.issued ? "Issued" : "Available"));

                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    // Issue Book
    static void issueBook() {

        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        for (Book book : books) {

            if (book.id == id) {

                if (book.issued) {
                    System.out.println("Book is already issued.");
                } else {
                    book.issued = true;
                    System.out.println("Book issued successfully!");
                }

                return;
            }
        }

        System.out.println("Book ID not found.");
    }

    // Return Book
    static void returnBook() {

        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        for (Book book : books) {

            if (book.id == id) {

                if (!book.issued) {
                    System.out.println("This book was not issued.");
                } else {
                    book.issued = false;
                    System.out.println("Book returned successfully!");
                }

                return;
            }
        }

        System.out.println("Book ID not found.");
    }
              }
