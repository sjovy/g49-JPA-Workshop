package se.lexicon.g49jpaworkshop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import se.lexicon.g49jpaworkshop.entity.Details;

import java.time.LocalDate;
import java.util.Optional;

public interface DetailsRepository extends JpaRepository<Details, Integer> {
    Optional<Details> findDetailsByEmailIgnoreCase(String email);
    Optional<Details> findDetailsByNameContains(String name);
    Optional<Details> findDetailsByNameContainsIgnoreCase(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Details d SET d.name = ?1, d.email = ?2, d.birthDay = ?3 WHERE d.id = ?4")
    int updateDetailsById(String name, String email, LocalDate birthday, int id);
}
