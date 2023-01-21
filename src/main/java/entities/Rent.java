package entities;

import db.Database;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import static entities.Book.session;
import static org.example.Main.errorMessage;

@Entity(name = "rent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int r_id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "isbn")
    private Book book;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "issue_date")
    private Timestamp issueDate;

    @Column(name = "due_date")
    private Timestamp dueDate;

    @Column(name = "isreturned")
    private boolean isReturned;

    // getters and setters

    public Rent(Book book, Client client, Timestamp issueDate, Timestamp dueDate, boolean isReturned) {
        this.book = book;
        this.client = client;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.isReturned = isReturned;
    }


    // The system should keep track of the due dates for books and
    // send reminders to users when a book is due soon.

    public static String dueDate() {
        //Date today = new Date();
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 14);  // number of days to add
        String dueDate = (String) (formattedDate.format(c.getTime()));
        System.out.println("Your due date is: " + dueDate);


        return dueDate;
    }

    public static String issueDate() {
        //Date today = new Date();
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar c = Calendar.getInstance();
        String issueDate = (String) (formattedDate.format(c.getTime()));
        System.out.println("Your issue date is: " + issueDate);


        return issueDate;
    }


    public static void createNewRent(int c_id, int b_id) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        try {
            Client client = session.get(Client.class, c_id);
            Book book = session.get(Book.class, b_id);
            LocalDateTime rentDate = LocalDateTime.now();
            LocalDateTime returnDate = rentDate.plusDays(7);

            Rent rent = new Rent();
            rent.setClient(client);
            rent.setBook(book);
            rent.setIssueDate(Timestamp.valueOf("2023-01-17 12:02:12"));
            rent.setReturnDate(returnDate);
            session.save(rent);
            session.flush();
            trans.commit();
            System.out.println("Book rent successfull");
        } catch (Exception e) {
            trans.rollback();
            errorMessage(e);
            e.printStackTrace();
        }
    }

    private void setReturnDate(LocalDateTime returnDate) {
    }



    public static void listRent() {
        Session session = Database.getHibSesh();

        try {
            session.beginTransaction();
            List<Rent> rents = session.createQuery("from rent").list();

            for (Rent rent : rents) {
                System.out.println(rent);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            errorMessage(e);
            e.printStackTrace();
        }
    }
}