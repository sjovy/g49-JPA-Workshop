package se.lexicon.g49jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String isbn;

    @Column
    private String title;

    @Column
    private int maxLoanDays;

    @Column
    private boolean available = true;

    @OneToMany(mappedBy = "book")
    private List<BookLoan> bookLoans;

    @ManyToMany(mappedBy = "writtenBooks")
    private Set<Author> authors;


    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public void addAuthor(Author author){
        if (author != null && !this.authors.contains(author)){
            this.authors.add(author);
            author.getWrittenBooks().add(this);
        }
    }

    public void removeAuthor(Author author){
        if (author != null && this.authors.contains(author)){
            this.authors.remove(author);
            author.getWrittenBooks().remove(this);
        }
    }
}
