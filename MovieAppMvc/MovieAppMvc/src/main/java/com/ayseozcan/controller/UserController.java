package com.ayseozcan.controller;

import com.ayseozcan.dto.request.FavMovieRequestDto;
import com.ayseozcan.dto.request.UserLoginRequestDto;
import com.ayseozcan.dto.request.UserRegisterRequestDto;
import com.ayseozcan.dto.response.MovieAdminPageResponseDto;
import com.ayseozcan.dto.response.UserLoginResponseDto;
import com.ayseozcan.mapper.IMovieMapper;
import com.ayseozcan.repository.entity.User;
import com.ayseozcan.repository.entity.UserType;
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
    private final MovieController movieController;
    private final MovieService movieService;

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
            if (responseDto.getUserType().equals(UserType.ADMIN)) {
                return adminPage();
            }
            return movieController.getMoviePage(responseDto,null);

        } catch (Exception e) {
            modelAndView.addObject("result", e.getMessage());
            modelAndView.setViewName("loginSayfasi");
        }
        return modelAndView;
    }

    @GetMapping("/admin")
    private ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAllUser();
        modelAndView.addObject("users", users);
        List<MovieAdminPageResponseDto> movies = IMovieMapper.INSTANCE.toMovieAdminPageResponseDto(movieService.findAll());
        modelAndView.addObject("movies",movies);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @GetMapping("/addfavmovies")
    public ModelAndView addFavMovies(FavMovieRequestDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        userService.addFavMovies(dto);
        modelAndView.addObject("id",dto.getMovieId());
        modelAndView.addObject("userId",dto.getUserId());
        modelAndView.setViewName("redirect:/movie/findbyid");
        return modelAndView;
    }
    @GetMapping("/removefavmovies")
    public ModelAndView removeFavMovies(FavMovieRequestDto dto){
        ModelAndView modelAndView = new ModelAndView();
        userService.removeFavmovies(dto);
        modelAndView.addObject("id",dto.getMovieId());
        modelAndView.addObject("userId",dto.getUserId());
        modelAndView.setViewName("redirect:/movie/findbyid");
        return modelAndView;
    }

}