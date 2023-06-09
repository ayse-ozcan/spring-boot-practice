package com.ayseozcan.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "tbluser")
public class User {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
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
