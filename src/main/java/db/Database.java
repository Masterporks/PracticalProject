package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

   /*
    public static Connection DbConn() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://babar.db.elephantsql.com/ehctpjci",
                    "ehctpjci",
                    "V2jHoWhXqw9y_DMuznaZgr4WIJI2DbDo");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    */

    public static Session getHibSesh() {

        Session session = null;

        try {
            SessionFactory seshFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

            session = seshFactory.openSession();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return session;

    }
}