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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToMany
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> writtenBooks;

    public void addBook(Book book) {
        if (book != null && !this.writtenBooks.contains(book)) {
            this.writtenBooks.add(book);
            book.getAuthors().add(this);
        }
    }

    public void removeBook(Book book) {
        if (book != null && this.writtenBooks.contains(book)) {
            this.writtenBooks.remove(book);
            book.getAuthors().remove(this);
        }
    }
}
