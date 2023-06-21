package com.ayseozcan.repository;

import com.ayseozcan.repository.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findAllByGenres_NameIn(String[] genre);
}