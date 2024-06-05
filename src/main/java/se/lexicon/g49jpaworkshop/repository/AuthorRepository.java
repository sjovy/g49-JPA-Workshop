package se.lexicon.g49jpaworkshop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import se.lexicon.g49jpaworkshop.entity.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAuthorByFirstNameIgnoreCase(String firstName);
    List<Author> findAuthorByLastNameIgnoreCase(String lastName);
    List<Author> findAuthorByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String keyword1, String keyword2);
    List<Author> findByWrittenBooks_id(int book_id);
    @Transactional
    @Modifying
    @Query("UPDATE Details d SET d.name = ?1, d.email = ?2, d.birthDay = ?3 WHERE d.id = ?4")
    int updateDetailsById(String name, String email, LocalDate birthday, int id);
}
