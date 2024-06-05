package se.lexicon.g49jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column
    private LocalDate regDate;


    @OneToOne
    @JoinColumn(name = "details_id")
    private Details userDetails;

    @OneToMany(mappedBy = "borrower")
    private List<BookLoan> bookLoans;


    public AppUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void borrowBook(Book book){
        if (book == null) throw new IllegalArgumentException("Book cannot be null");
        if (book.isAvailable()){
            BookLoan bookLoan = new BookLoan(LocalDate.now(), this, book);
            this.bookLoans.add(bookLoan);
            book.setAvailable(false);
        }else {
            throw new IllegalArgumentException("Book is not available");
        }
    }
}
