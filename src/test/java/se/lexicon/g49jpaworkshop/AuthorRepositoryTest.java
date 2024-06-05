package se.lexicon.g49jpaworkshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g49jpaworkshop.entity.Author;
import se.lexicon.g49jpaworkshop.entity.Book;
import se.lexicon.g49jpaworkshop.entity.Details;
import se.lexicon.g49jpaworkshop.repository.AuthorRepository;
import se.lexicon.g49jpaworkshop.repository.BookRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    private Author author;
    private Book book;

    @BeforeEach
    public void setup() {
        author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        authorRepository.save(author);

        book = new Book();
        book.setAuthors(new HashSet<>(Arrays.asList(author)));
        book.setTitle("title");
        Book savedBook = bookRepository.save(book);
    }

    @Test
    public void test_findAuthorByFirstNameIgnoreCase() {
        // Execute: Find the author by first name
        List<Author> foundAuthors = authorRepository.findAuthorByFirstNameIgnoreCase("john");

        // Assert: Check if the found author is the one we saved
        assertTrue(foundAuthors.stream().anyMatch(a -> a.getFirstName().equalsIgnoreCase("john")));
    }

    @Test
    public void test_findAuthorByLastNameIgnoreCase() {
        // Execute: Find the author by last name
        List<Author> foundAuthors = authorRepository.findAuthorByLastNameIgnoreCase("doe");

        // Assert: Check if the found author is the one we saved
        assertTrue(foundAuthors.stream().anyMatch(a -> a.getLastName().equalsIgnoreCase("doe")));
    }

    @Test
    public void test_findAuthorByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase() {
        // Execute: Find the author by first name or last name containing a keyword
        List<Author> foundAuthors = authorRepository.findAuthorByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("jo", "do");

        // Assert: Check if the found author is the one we saved
        assertTrue(foundAuthors.stream().anyMatch(a -> a.getFirstName().equalsIgnoreCase("john") || a.getLastName().equalsIgnoreCase("doe")));
    }

    @Test
    public void test_findByWrittenBooks_id() {
        author.addBook(book);
        // Execute: Find the author by the book's id
        List<Author> foundAuthors = authorRepository.findByWrittenBooks_id(book.getId());

        // Assert: Check if the found author is the one we saved
        assertTrue(foundAuthors.stream().anyMatch(a -> a.getId() == author.getId()));
    }

    @Test
    public void test_updateDetailsById() {
        int authorId = author.getId();
        authorRepository.updateAuthorById("Update_FirstName", "Update_LastName", authorId);

        Author authorOneCopy = author;
        authorOneCopy.setFirstName("Update_FirstName");
        authorOneCopy.setLastName("Update_LastName");

        Optional<Author> expected = Optional.of(authorOneCopy);
        Optional<Author> actual = authorRepository.findById(authorId);

        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}