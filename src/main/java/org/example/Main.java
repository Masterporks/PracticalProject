package org.example;

import db.Database;
import entities.Book;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

        Session session = Database.getHibSesh();

        //    Book.listBook();




    }



}