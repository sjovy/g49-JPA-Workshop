package se.lexicon.g49jpaworkshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g49jpaworkshop.entity.AppUser;
import se.lexicon.g49jpaworkshop.entity.Book;
import se.lexicon.g49jpaworkshop.entity.BookLoan;
import se.lexicon.g49jpaworkshop.repository.AppUserRepository;
import se.lexicon.g49jpaworkshop.repository.BookLoanRepository;
import se.lexicon.g49jpaworkshop.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookLoanRepositoryTest {
    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    private BookLoan testBookLoan1;
    private BookLoan testBookLoan2;
    private BookLoan testBookLoan3;
    private AppUser user;
    private Book book1;
    private Book book2;
    private Book book3;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        user = new AppUser("Test","test123");
        appUserRepository.save(user);
        book1 = new Book("123","test title",42);
        book2 = new Book("321","test title1",21);
        book3 = new Book("456","test title3",2);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        testBookLoan1 = new BookLoan(LocalDate.now(),user,book1);
        testBookLoan2 = new BookLoan(LocalDate.now(),user,book2);
        testBookLoan3 = new BookLoan(LocalDate.parse("2024-05-01"),user,book3);
        testBookLoan2.setReturned(false);
        testBookLoan1.setReturned(true);

        bookLoanRepository.save(testBookLoan1);
        bookLoanRepository.save(testBookLoan2);
        bookLoanRepository.save(testBookLoan3);
    }

    @Test
    public void findByBorrowerId_ShouldReturnListOfBookLoans() {
        Integer borrowerId = user.getId();
        List<BookLoan> result = bookLoanRepository.findByBorrowerId(borrowerId);
        assertNotNull(result);
        assertTrue(result.contains(testBookLoan1));
        assertTrue(result.contains(testBookLoan2));
    }

    @Test
    public void findByBookId_ShouldReturnListOfBookLoans() {
        Integer bookId = book1.getId();
        List<BookLoan> result = bookLoanRepository.findByBookId(bookId);
        assertNotNull(result);
        assertTrue(result.contains(testBookLoan1));
        assertFalse(result.contains(testBookLoan2));
    }

    @Test
    public void findByBookNotReturned_ShouldReturnListOfBookLoans() {
        List<BookLoan> result = bookLoanRepository.findByBookNotReturned();
        assertNotNull(result);
        assertTrue(result.contains(testBookLoan2));
    }

    @Test
    public void findOverDueBookLoans_ShouldReturnListOfBookLoans() {
        List<BookLoan> result = bookLoanRepository.findOverDueBookLoans();
        assertNotNull(result);
        assertTrue(result.contains(testBookLoan3));
    }

    @Test
    public void findByBookLoansBetween_ShouldReturnListOfBookLoans() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.parse("2024-06-30");
        List<BookLoan> result = bookLoanRepository.findByBookLoansBetween(startDate, endDate);
        assertNotNull(result);
        assertTrue(result.contains(testBookLoan1));
        assertTrue(result.contains(testBookLoan2));
    }

    @Test
    public void markAsReturned_ShouldUpdateBookLoan() {
        Integer loanId = testBookLoan1.getId();
        bookLoanRepository.markAsReturned(loanId);
        Optional<BookLoan> result = bookLoanRepository.findById(loanId);
        assertTrue(result.isPresent());
        assertTrue(result.get().isReturned());
    }
}