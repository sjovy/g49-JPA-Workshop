package se.lexicon.g49jpaworkshop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.g49jpaworkshop.entity.AppUser;
import se.lexicon.g49jpaworkshop.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookLoanRepository extends JpaRepository<Boolean, Integer> {

    @Query("SELECT bl FROM AppUser a JOIN a.bookLoans bl WHERE a.id = :borrowerId")
    List<BookLoan> findByBorrowerId(@Param("borrowerId") Integer borrowerId);

    @Query("SELECT bl FROM Book b JOIN b.bookLoans bl WHERE b.id = :bookId")
    List<BookLoan> findByBookId(@Param("bookId") Integer bookId);

    @Query("SELECT b FROM BookLoan b WHERE b.returned = FALSE")
    List<BookLoan> findByBookNotReturned();

    @Query("SELECT b FROM BookLoan b WHERE b.dueDate < current_date AND b.returned = false ")
    List<BookLoan> findOverDueBookLoans();

    @Query("SELECT b FROM BookLoan b WHERE b.loanDate BETWEEN :startDate AND :endDate")
    List<BookLoan> findByBookLoansBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
@Modifying
@Transactional
@Query("UPDATE BookLoan b SET b.returned = TRUE WHERE b.id = :loanId")
    void  markAsReturned(@Param("loanId")Integer loanId);
}
