package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "rent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rent {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int rentId;

    @OneToOne
    @JoinColumn(name = "isbn")
    private Book isbn;

    @Column(name = "return_date")
    private Timestamp returnDate;

    @Column(name = "due_date")
    private Timestamp dueDate;


    @JoinColumn(name = "client_id")
    private Client clientId;

    @Column (name = "isReturned")
    private boolean isReturned;

    public Rent(Book isbn, Timestamp returnDate, Timestamp dueDate, Client clientId, boolean isReturned) {
        this.isbn = isbn;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
        this.clientId = clientId;
        this.isReturned = isReturned;
    }


    // keep track


    // reminder






}