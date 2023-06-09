package com.ayseozcan.service;

import com.ayseozcan.repository.IGenreRepository;
import com.ayseozcan.repository.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final IGenreRepository genreRepository;

    public List<Genre> createGenresWithName(List<String> genres) {
        List<Genre> genreList = new ArrayList<>();
        for(String name: genres ){
            Optional<Genre> genre = genreRepository.findOptianalByName(name);
            if(genre.isPresent()){
                genreList.add(genre.get());
            }else{
                Genre genre1 = Genre.builder().name(name).build();
                genreRepository.save(genre1);
                genreList.add(genre1);
            }
        }
        return genreList;
    }
}
