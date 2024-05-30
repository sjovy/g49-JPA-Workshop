package se.lexicon.g49jpaworkshop.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity

public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private LocalDate birthDay;
}
