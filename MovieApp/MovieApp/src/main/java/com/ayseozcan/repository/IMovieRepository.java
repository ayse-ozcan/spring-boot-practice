package com.ayseozcan.repository;

import com.ayseozcan.repository.entity.Genre;
import com.ayseozcan.repository.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findAllByRatingGreaterThan(double rating);
    List<Movie> findAllByGenresInOrderByRatingDesc(List<Genre> genres);
    List<Movie> findAllByPremieredBefore(LocalDate premier);
    @Query("select count(m.rating), m.rating from Movie m group by m.rating having m.rating =?1 ")
    Object[] findRatingByCounting(double rate);
    @Query("select count(m.rating), m.rating from Movie m group by m.rating")
    List<Object> findRatingByCounting2();
    @Query("select m.name, m.rating from Movie m where m.rating in (7,8,9) order by m.name")
    List<Object> findRatingMovie();
    List<Movie> findAllByRatingIn(List<Double> ratings);
    List<Movie> findAllByNameIn(String[] names);
}