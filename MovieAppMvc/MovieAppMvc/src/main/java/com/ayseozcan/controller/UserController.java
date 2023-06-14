package com.ayseozcan.controller;

import com.ayseozcan.dto.request.UserLoginRequestDto;
import com.ayseozcan.dto.request.UserRegisterRequestDto;
import com.ayseozcan.dto.response.UserLoginResponseDto;
import com.ayseozcan.repository.entity.Genre;
import com.ayseozcan.repository.entity.Movie;
import com.ayseozcan.service.GenreService;
import com.ayseozcan.service.MovieService;
import com.ayseozcan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MovieService movieService;
    private final GenreService genreService;

    @GetMapping("/registerpage")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register"); //html name
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterRequestDto dto) {
        String error = "";
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.save(dto);
            modelAndView.addObject("email", dto.getEmail());
            modelAndView.setViewName("loginSayfasi");
        } catch (Exception ex) {
            error = ex.getMessage();
            modelAndView.setViewName("register");
            modelAndView.addObject("error", error);
        }
        return modelAndView;
    }

    @GetMapping("/loginpage")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginSayfasi");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginRequestDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
           UserLoginResponseDto responseDto = userService.login(dto);
            List<Movie> movieList = movieService.findAll();
            List<Genre> genreList = genreService.findAll();

            //modelAndView.addObject("result","Giris Basarili");
            modelAndView.addObject("movieListesi",movieList);
            modelAndView.addObject("genreListesi",genreList);
            modelAndView.addObject("userInfo",responseDto);
            modelAndView.setViewName("movies");
        } catch (Exception e) {
            modelAndView.addObject("result",e.getMessage());
            modelAndView.setViewName("loginSayfasi");
        }
        return modelAndView;

    }
}