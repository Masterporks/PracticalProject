package entities;
import db.Database;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;

import java.util.List;

import static entities.Book.session;


@Entity(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    static Session session = Database.getHibSesh();


    public static void addClient(Client client) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        try {

            session.persist(client);
            session.flush();
            trans.commit();

        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }

    }

    public static void updateClient(int clientId) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Client client = session.get(Client.class, clientId);
        client.setEmail(client.email);

        try {
            session.merge(client);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static void deleteClient(int clientId) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Client client = session.get(Client.class, clientId);

        try {
            session.delete(client);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static void listClient() {
        Session session = Database.getHibSesh();

        try {
            session.beginTransaction();
            List<Client> clients = session.createQuery("from client").list();

            for (Client client : clients) {
                System.out.println(client);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
