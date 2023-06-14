package com.ayseozcan.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbluser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String surname;
    @Column(length = 50)
    private String email;
    @Column(length = 15)
    private String phone;
    @Column(length = 32)
    private String password;
    @ManyToMany
    private List<Movie> favMovies;
    @ManyToMany
    private List<Genre> favGenres;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<MovieComment> comments;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserType userType = UserType.USER;
}
