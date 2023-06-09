package com.ayseozcan.service;

import com.ayseozcan.repository.IMovieRepository;
import com.ayseozcan.repository.entity.Genre;
import com.ayseozcan.repository.entity.Movie;
import com.ayseozcan.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final IMovieRepository movieRepository;
    private final UserService userService;

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie findbyId(long id) {
        Optional<Movie> optional = movieRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("film bulunamadi");
        }
    }

    public List<Movie> findAllByIds(List<Long> ids) {
        return ids.stream().map(x -> movieRepository.findById(x).get()).collect(Collectors.toList());
    }

    public List<Movie> findAllByRatingGreaterThan(double rating) {
        return movieRepository.findAllByRatingGreaterThan(rating);
    }

    public List<Movie> findMoviesByUserGenres(Long id) {
        Optional<User> optionalUser = userService.findById(id);
        List<Genre> genreList = optionalUser.get().getFavGenres();
        if (genreList.size() > 0) {
            return movieRepository.findAllByGenresInOrderByRatingDesc(genreList);
        } else {
            throw new RuntimeException("favlanan genre yok");
        }
    }

    public List<Movie> findAllByPremieredBefore(LocalDate premier) {
        return movieRepository.findAllByPremieredBefore(premier);
    }
    public Object[] findRatingByCounting(double rate){
        return movieRepository.findRatingByCounting(rate);
    }
    public List<Object> findRatingByCounting2(){
        return movieRepository.findRatingByCounting2();
    }
    public List<Object> findRatingMovie(){
        return movieRepository.findRatingMovie();
    }
    public List<Movie> findAllByRatingIn(){
        List<Double> ratings = List.of(7D,8D,9D);
        return movieRepository.findAllByRatingIn(ratings);
    }
    public List<Movie> findAllByNameIn(String[] names){
        return movieRepository.findAllByNameIn(names);
    }
}
