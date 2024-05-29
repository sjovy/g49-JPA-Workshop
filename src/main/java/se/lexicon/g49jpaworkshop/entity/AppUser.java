package se.lexicon.g49jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data

public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column
    private LocalDate regDate;

    @Column
    @OneToOne
    @JoinColumn(name = "details_id")
    private Details userDetails;

}
