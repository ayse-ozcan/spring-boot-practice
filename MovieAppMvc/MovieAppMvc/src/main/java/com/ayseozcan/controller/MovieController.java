package com.ayseozcan.controller;

import com.ayseozcan.dto.response.UserLoginResponseDto;
import com.ayseozcan.repository.entity.Genre;
import com.ayseozcan.repository.entity.Movie;
import com.ayseozcan.repository.entity.User;
import com.ayseozcan.service.GenreService;
import com.ayseozcan.service.MovieService;
import com.ayseozcan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final GenreService genreService;
    private final UserService userService;


    @GetMapping("")
    public ModelAndView getMoviePage(UserLoginResponseDto responseDto, String[] genres) {
        ModelAndView modelAndView = new ModelAndView();
        List<Movie> movieList = null;
        List<Genre> genreList = genreService.findAll();
        if (genres == null) {
            movieList = movieService.findAll();
        } else {
            movieService.findAllByGenres_NameIn(genres);
        }

        //modelAndView.addObject("result","Giris Basarili");
        modelAndView.addObject("movieListesi", movieList);
        modelAndView.addObject("genreListesi", genreList);
        modelAndView.addObject("userInfo", responseDto);
        modelAndView.setViewName("movies");
        return modelAndView;
    }

    @GetMapping("/findbyid")
    public ModelAndView getById(Long id, Long userId) {
        User user = null;
        if (userId == null) {
            user = User.builder().build();
        } else {
            user = userService.findById(userId).get();
        }
        Movie movie = movieService.findbyId(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("movie", movie);
        modelAndView.addObject("userInfo", user);
        //http://localhost:9090/movie/findbyid?id=1&userId=3
        //th:href="${'/movie/findbyid?id='+movie.id+ '&userId=' +userInfo.id}"
        modelAndView.setViewName("moviesDetail");
        return modelAndView;
    }


}
