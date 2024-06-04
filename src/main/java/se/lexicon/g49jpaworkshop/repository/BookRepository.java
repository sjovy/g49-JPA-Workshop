package se.lexicon.g49jpaworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.g49jpaworkshop.entity.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByTitleContainsIgnoreCase(String title);

    List<Book> findByMaxLoanDaysIsLessThan(int maxLoanDays);

    @Query("SELECT b FROM Book  b JOIN b.bookLoans bl WHERE bl.returned = false")
    List<Book> findByBookLoans_ReturnedFalse();

    @Query("SELECT b FROM Book b JOIN b.bookLoans bl WHERE bl.dueDate < CURRENT_DATE AND bl.returned = false")
    List<Book> findOverdueBooks();

    @Query("SELECT b FROM Book b JOIN b.bookLoans bl WHERE bl.loanDate BETWEEN :startDate AND :endDate")
List<Book> findByBookLoansBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
