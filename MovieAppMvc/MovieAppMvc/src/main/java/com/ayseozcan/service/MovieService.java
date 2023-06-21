package com.ayseozcan.service;

import com.ayseozcan.repository.IMovieRepository;
import com.ayseozcan.repository.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovieService {
    private final IMovieRepository movieRepository;
    public List<Movie> findAll(){
       return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }
    public List<Movie> findAllByIds(List<Long> ids) {
        return ids.stream().map(x -> movieRepository.findById(x).get()).collect(Collectors.toList());
    }
    public Movie findbyId(long id) {
        Optional<Movie> optional = movieRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("film bulunamadi");
        }
    }
    public List<Movie> findAllByGenres_NameIn(String[] genre){
        return movieRepository.findAllByGenres_NameIn(genre);
    }
}
