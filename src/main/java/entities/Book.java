package entities;

import db.Database;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Entity(name = "book")
@Data
@NoArgsConstructor
public class Book {
    static Session session = Database.getHibSesh();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int b_id;

    @Column(name = "book_title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "qty_in_library")
    private int qtyInLibrary;


    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "isAvailable")
    private boolean isAvailable;

    public Book(String title, String category, int qtyInLibrary, int authorId, boolean isAvailable) {
        this.title = title;
        this.category = category;
        this.qtyInLibrary = qtyInLibrary;
        this.author.setA_id(authorId);
        this.isAvailable = isAvailable;
    }


    public static void addBook(Book book) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        try {
            session.persist(book);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static void updateBook(String title, int isbn) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Book book = session.get(Book.class, isbn);
        book.setTitle(title);

        try {
            session.merge(book);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static void deleteBook(int isbn) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Book book = session.get(Book.class, isbn);

        try {
            session.delete(book);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    // Collections can be used to store the books in the library's collection.
    public static void listBook() {

        try {
            session.beginTransaction();
            List<Book> books = session.createQuery("from book").list();

            for (Book book : books) {
                System.out.println(book);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Scanner sc = new Scanner(System.in);

    // Users should be able to search and view books in the library's collection by entering the ISBN
    // or title of the book.


    // Admins should be able to add, update, and delete books from the collection,
    // as well as manage user accounts via the console interface.


    // Users should be able to check out and return books by entering the ISBN of the book,
    // with the option to place a hold on a book if it is currently checked out.
    public static void checkOutBook(int isbn) {

        Session session = Database.getHibSesh();

        try {
            System.out.println("Insert the isbn of the book you would like to issue?");
            isbn = sc.nextInt();
            session.beginTransaction();
            Book book = session.get(Book.class, isbn);
            Rent rent = session.get(Rent.class, isbn);
            System.out.println("Insert your id:");
            int cust_id = sc.nextInt();
            if (book.isAvailable) {
                System.out.println("Are you sure you want to check out this book? (yes/no)");
                Scanner scanner = new Scanner(System.in);
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    System.out.println("How many books you want?");
                    int qty = scanner.nextInt();
                    book.setQtyInLibrary(book.getQtyInLibrary() - qty);
                    book.setAvailable(false);
                    //Rent rent1 = new Rent();
                    rent.setIssueDate(Timestamp.valueOf(Rent.issueDate()));
                    rent.setDueDate(Timestamp.valueOf(Rent.dueDate()));

                    session.merge(book);
                    session.merge(rent);
                    session.flush();
                    System.out.println("Book checked out successfully!");
                } else {
                    System.out.println("Check out cancelled.");
                }
            } else {
                System.out.println("Book is not available.");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void returnBook(int isbn) {
        Session session = Database.getHibSesh();
        try {
            session.beginTransaction();
            Book book = session.get(Book.class, isbn);
            if (!book.isAvailable) {
                System.out.println("Are you sure you want to return this book? (yes/no)");
                Scanner scanner = new Scanner(System.in);
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    System.out.println("How many books you want return?");
                    int qty = scanner.nextInt();
                    book.setQtyInLibrary(book.getQtyInLibrary() + qty);
                    book.setAvailable(true);
                    session.merge(book);
                    session.flush();
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Return cancelled.");
                }
            } else {
                System.out.println("Book has not been checked out.");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkoutBookByTitle(String title) {
        Session session = Database.getHibSesh();


        try {
            session.beginTransaction();
            List<Book> books = session.createQuery("from book where title = :title", Book.class)
                    .setParameter("title", title)
                    .getResultList();
            if (books.size() > 0) {
                Book book = books.get(0);
                if (book.isAvailable) {
                    System.out.println("Are you sure you want to checkout this book? (yes/no)");
                    Scanner scanner = new Scanner(System.in);
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        System.out.println("How many books you want checkout?");
                        int qty = scanner.nextInt();
                        book.setQtyInLibrary(book.getQtyInLibrary() - qty);
                        book.setAvailable(true);
                        session.merge(book);
                        session.flush();
                        System.out.println("Checkout was successfully!");
                    } else {
                        System.out.println("Checkout cancelled.");
                    }
                } else {
                    System.out.println("Book has not been checked out.");
                }
            } else {
                System.out.println("No book found with that title.");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void returnBookByTitle(String title) {
        Session session = Database.getHibSesh();


        try {
            session.beginTransaction();
            List<Book> books = session.createQuery("from book where title = :title", Book.class)
                    .setParameter("title", title)
                    .getResultList();
            if (books.size() > 0) {
                Book book = books.get(0);
                if (book.isAvailable) {
                    System.out.println("Are you sure you want to return this book? (yes/no)");
                    Scanner scanner = new Scanner(System.in);
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        System.out.println("How many books you want checkout?");
                        int qty = scanner.nextInt();
                        book.setQtyInLibrary(book.getQtyInLibrary() + qty);
                        book.setAvailable(true);
                        session.merge(book);
                        session.flush();
                        System.out.println("Book returned successfully!");
                    } else {
                        System.out.println("Return cancelled.");
                    }
                } else {
                    System.out.println("Book has not been returned.");
                }
            } else {
                System.out.println("No book found with that title.");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


//       session.update(qtyInLibrary - qty); - if the rent is made
