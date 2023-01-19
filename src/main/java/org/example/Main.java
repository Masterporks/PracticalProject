package org.example;

import db.Database;
import entities.Book;
import entities.Client;
import entities.Rent;
import org.hibernate.Session;

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
        Book.listBook();
        //Book.checkoutBookByTitle(sc.nextLine());
        //Book.returnBookByTitle(sc.nextLine());

        //Rent rent = session.find(Rent.class, 1);

        //Book book = session.find(Book.class, isbn);
        //Client client = session.find(Client.class, client_id);


        //Rent rent = new Rent(Timestamp.valueOf("2023-01-17 12:02:12"), Timestamp.valueOf("2023-01-17 12:02:12"));
        //Rent.createNewRent(rent);









    }
}

