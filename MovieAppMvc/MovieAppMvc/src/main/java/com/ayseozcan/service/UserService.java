package com.ayseozcan.service;


import com.ayseozcan.dto.request.UserLoginRequestDto;
import com.ayseozcan.dto.request.UserRegisterRequestDto;
import com.ayseozcan.dto.response.UserLoginResponseDto;
import com.ayseozcan.dto.response.UserRegisterResponseDto;
import com.ayseozcan.mapper.IUserMapper;
import com.ayseozcan.repository.IUserRepository;
import com.ayseozcan.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;

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
}

