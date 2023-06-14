package com.ayseozcan.repository;

import com.ayseozcan.repository.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long> {
}