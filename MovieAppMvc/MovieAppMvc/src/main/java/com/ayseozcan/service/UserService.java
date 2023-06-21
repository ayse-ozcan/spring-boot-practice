package com.ayseozcan.service;


import com.ayseozcan.dto.request.FavMovieRequestDto;
import com.ayseozcan.dto.request.UserLoginRequestDto;
import com.ayseozcan.dto.request.UserRegisterRequestDto;
import com.ayseozcan.dto.response.UserLoginResponseDto;
import com.ayseozcan.dto.response.UserRegisterResponseDto;
import com.ayseozcan.mapper.IUserMapper;
import com.ayseozcan.repository.IUserRepository;
import com.ayseozcan.repository.entity.Movie;
import com.ayseozcan.repository.entity.User;
import com.ayseozcan.repository.entity.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final MovieService movieService;

    public UserRegisterResponseDto save(UserRegisterRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Bu email zaten kullaniliyor");
        } else {
            if (!dto.getPassword().equals(dto.getRePassword())) {
                throw new RuntimeException("Password ler uyusmuyor");
            }
            User user = IUserMapper.INSTANCE.toUser(dto);
            userRepository.save(user);
            return IUserMapper.INSTANCE.toRegisterResponseDto(user);
        }
    }

    public UserLoginResponseDto login(UserLoginRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCaseAndPassword(dto.getEmail(), dto.getPassword());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("email ya da password bilgisi yanlis");
        }
        return IUserMapper.INSTANCE.toLoginResponseDto(optionalUser.get());
    }

    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public void addFavMovies(FavMovieRequestDto dto) {
        Optional<User> userOptional = userRepository.findById(dto.getUserId());
        Movie movie = movieService.findbyId(dto.getMovieId());
        if (userOptional.isPresent()) {
            if (!userOptional.get().getFavMovies().contains(movie)) {
                userOptional.get().getFavMovies().add(movie);
                userRepository.save(userOptional.get());
            }
        } else {
            throw new RuntimeException("kullanici bulunamadi");
        }
    }
    public void removeFavmovies(FavMovieRequestDto dto) {
        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        Movie movie = movieService.findbyId(dto.getMovieId());
        if(optionalUser.isPresent()){
            if(optionalUser.get().getFavMovies().contains(movie)){
                optionalUser.get().getFavMovies().remove(movie);
                userRepository.save(optionalUser.get());
            }
        }else {
            throw new RuntimeException("kullanici bulunamadi");
        }
    }
    public List<User> findAllUser() {
       return userRepository.findAll().stream().filter(x->!x.getUserType().equals(UserType.ADMIN))
                .collect(Collectors.toList());
    }
}

