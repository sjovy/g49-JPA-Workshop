package se.lexicon.g49jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDate loanDate;

    @Column
    private LocalDate dueDate;

    @Column
    private boolean returned;


    @ManyToOne
    @JoinColumn(name = "appUser_id")
    private AppUser borrower;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
