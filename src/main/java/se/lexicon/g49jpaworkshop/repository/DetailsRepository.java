package se.lexicon.g49jpaworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g49jpaworkshop.entity.Details;

import java.util.Optional;

public interface DetailsRepository extends JpaRepository<Details, Integer> {
    Optional<Details> findDetailsByEmailIgnoreCase(String email);
    Optional<Details> findDetailsByNameContains(String name);
    Optional<Details> findDetailsByNameContainsIgnoreCase(String name);
}
