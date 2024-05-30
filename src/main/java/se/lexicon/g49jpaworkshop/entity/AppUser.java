package se.lexicon.g49jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

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


    @OneToOne
    @JoinColumn(name = "details_id")
    private Details userDetails;

    public AppUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
