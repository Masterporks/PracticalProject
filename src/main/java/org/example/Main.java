package org.example;

import db.Database;
import entities.Book;
import entities.Client;
import entities.Rent;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Session session = Database.getHibSesh();
        //System.out.println("Insert the isbn of the book you would like to return?");
        //Book.returnBook(sc.nextInt());

        /*Rent rent = new Rent ();
        rent.setIssueDate(Timestamp.valueOf("2023-01-17 12:02:12"));
        rent.setDueDate(Timestamp.valueOf("2023-01-31 12:02:12"));
        rent.getClient_id();
        rent.getIsbn();
        rent.isReturned();
        Rent.createNewRent(rent);
         */
        //Book.checkOutBook(sc.nextInt());
        //Book.checkOutBook(sc.nextInt());
       // Book.listBook();
        Book.checkoutBookByTitle(sc.nextLine());
        //Book.returnBookByTitle(sc.nextLine());

        //Rent rent = session.find(Rent.class, 1);

        //Book book = session.find(Book.class, isbn);
        //Client client = session.find(Client.class, client_id);


        //Rent rent = new Rent(new Book(),new Client(),Timestamp.valueOf("2023-01-17 12:02:12"), Timestamp.valueOf("2023-01-17 12:02:12"),false);
        //Rent.createNewRent(3,15);









    }
    public static void errorMessage(Exception e) {
        Session session = Database.getHibSesh();
        if (e instanceof NumberFormatException) {
            System.out.println("Error: Please enter a valid number.");
        } else if (e instanceof SQLException) {
            System.out.println("Error: There was a problem with the database connection, please try again.");
        } else if (e instanceof ConstraintViolationException) {
            System.out.println("Error: The input data violates a database constraint, please try again.");
        } else if (e instanceof IllegalArgumentException) {
            System.out.println("Error: Please enter a valid input and try again.");
        } else if (e.getMessage().contains("Invalid string input")) {
            System.out.println("Error: Please enter a valid string input.");
        } else {
            System.out.println("An error has occurred, please try again: " + e.getMessage());
        }
        e.printStackTrace();
        session.getTransaction().rollback();
    }
}

