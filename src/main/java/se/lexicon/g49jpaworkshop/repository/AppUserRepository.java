package se.lexicon.g49jpaworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g49jpaworkshop.entity.AppUser;
import se.lexicon.g49jpaworkshop.entity.Details;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUserByUserName(String userName);

    List<AppUser> findAppUserByRegDateBetween(LocalDate regDate1, LocalDate regDate2);

    Optional<AppUser> findAppUserByUserDetails_Id(int id);

    Optional<AppUser> findAppUserByUserDetails_EmailIgnoreCase(String email);
}
