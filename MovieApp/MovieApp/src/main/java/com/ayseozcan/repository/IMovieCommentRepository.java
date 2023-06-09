package com.ayseozcan.repository;

import com.ayseozcan.repository.entity.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovieCommentRepository extends JpaRepository<MovieComment,Long> {

    List<MovieComment> findByMovie_Id(Long id);
    @Query("select mc from MovieComment mc where mc.movie.name =?1 and mc.date > ?2 and mc.date < ?2")
    List<MovieComment> findByDateContent(String name, LocalDate fdate, LocalDate ldate);
    List<MovieComment> findAllByDateBetweenAndMovie_Id(LocalDate fdate, LocalDate ldate,Long id);
    List<MovieComment> findByUser_Id(Long id);
    List<MovieComment> findByDateBetweenAndUser_Id(LocalDate fdate, LocalDate ldate,Long id);
    List<MovieComment> findByContentContaining(String value);
    @Query("select mc from MovieComment mc where length(mc.content) > ?1")
    List<MovieComment> findByContentGreaterThan(int value);
}
