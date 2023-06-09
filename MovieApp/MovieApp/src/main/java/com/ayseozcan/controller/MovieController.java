package com.ayseozcan.controller;

import com.ayseozcan.repository.entity.Movie;
import com.ayseozcan.repository.entity.User;
import com.ayseozcan.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/ratinggreater")
    public ResponseEntity<List<Movie>> findAllByRatingGreaterThan(double rating){
    return ResponseEntity.ok(movieService.findAllByRatingGreaterThan(rating));
    }
    @GetMapping("/findmoviefavgenres")
    public ResponseEntity<List<Movie>> findMoviesByUserGenres(Long id){
        return ResponseEntity.ok(movieService.findMoviesByUserGenres(id));
    }
    @GetMapping("/premieredbefore")
    public ResponseEntity<List<Movie>> findAllByPremieredBefore(LocalDate premier){
        return ResponseEntity.ok(movieService.findAllByPremieredBefore(premier));
    }
    @GetMapping("/countrate")
    public ResponseEntity<Object[]> findRatingByCounting(double rate){
        return ResponseEntity.ok(movieService.findRatingByCounting(rate));
    }
    @GetMapping("/countrate2")
    public ResponseEntity<List<Object>> findRatingByCounting2(){
        return ResponseEntity.ok(movieService.findRatingByCounting2());
    }
    @GetMapping("/movierating")
    public ResponseEntity<List<Object>> findRatingMovie(){
        return ResponseEntity.ok(movieService.findRatingMovie());
    }
    @GetMapping("/movierating2")
    public ResponseEntity<List<Movie>> findAllByRatingIn(){
        return ResponseEntity.ok(movieService.findAllByRatingIn());
    }
    @GetMapping("/searchbyname")
    public ResponseEntity<List<Movie>> findAllByNameIn(String[] names){
        return ResponseEntity.ok(movieService.findAllByNameIn(names));
    }
}
