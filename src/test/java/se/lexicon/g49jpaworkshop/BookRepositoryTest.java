package se.lexicon.g49jpaworkshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g49jpaworkshop.entity.Book;
import se.lexicon.g49jpaworkshop.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    private Book testBook1;
    private Book testBook2;

    @BeforeEach
    public void setup() {
        testBook1 = new Book("123","test book",7);
        testBook2 = new Book("321","test Title 2", 16);


        bookRepository.save(testBook1);
        bookRepository.save(testBook2);
    }

    @Test
    public void findByIsbn_ShouldReturnOptionalBook() {
        String isbn = "123";
        Optional<Book> result = bookRepository.findByIsbn(isbn);
        assertTrue(result.isPresent());
        assertEquals(isbn, result.get().getIsbn());
    }

    @Test
    public void findByTitleContainsIgnoreCase_ShouldReturnOptionalBook() {
        Optional<Book> result = bookRepository.findByTitleContainsIgnoreCase("test book");
        assertTrue(result.isPresent());
        assertEquals(testBook1.getTitle(), result.get().getTitle());
    }

    @Test
    public void findByMaxLoanDaysIsLessThan_ShouldReturnListOfBooks() {
        List<Book> result = bookRepository.findByMaxLoanDaysIsLessThan(10);
        assertNotNull(result);
        assertTrue(result.contains(testBook1));
        assertFalse(result.contains(testBook2));
    }

}