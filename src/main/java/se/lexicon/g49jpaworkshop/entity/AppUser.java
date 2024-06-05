package se.lexicon.g49jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private List<BookLoan> bookLoans = new ArrayList<>();


    public AppUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean borrowBook(Book book){
        if (book == null) throw new IllegalArgumentException("Book cannot be null");
        if (book.isAvailable()){
            BookLoan bookLoan = new BookLoan(LocalDate.now(), this, book);
            this.bookLoans.add(bookLoan);
            book.setAvailable(false);
            return true;
        }else {
            System.out.println("The book is not available");
            return false;
            }
    }
}
