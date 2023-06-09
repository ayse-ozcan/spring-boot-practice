package com.ayseozcan.service;

import com.ayseozcan.dto.request.UserLoginRequestDto;
import com.ayseozcan.dto.request.UserRegisterRequestDto;
import com.ayseozcan.dto.response.UserFindAllResponseDto;
import com.ayseozcan.dto.response.UserLoginResponseDto;
import com.ayseozcan.dto.response.UserRegisterResponseDto;
import com.ayseozcan.exception.ErrorType;
import com.ayseozcan.exception.MovieAppException;
import com.ayseozcan.mapper.IUserMapper;
import com.ayseozcan.repository.IUserRepository;
import com.ayseozcan.repository.entity.User;
import com.ayseozcan.repository.entity.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;

    public User createUser(String name, String surname, String email, String phone, String password, String userType) {
        UserType userType1 = null;
        User user;
        try {
            userType1 = UserType.valueOf(userType);
            user = User.builder().name(name).surname(surname).
                    email(email).phone(phone).password(password).
                    userType(userType1).build();

        } catch (Exception e) {
            System.out.println("Boyle bir user type bulunamadi");
            user = User.builder().name(name).surname(surname).
                    email(email).phone(phone).password(password).
                    build();
        }
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    public List<User> findAllByOrderByName() {
        List<User> userList = userRepository.findAllByOrderByName();
        if (userList.size() > 0) {
            return userList;
        } else {
            throw new RuntimeException("veri bulunamadi");
        }
    }

    public List<User> findAllByNameContaining(String name) {
        return userRepository.findAllByNameContaining(name);
    }

    public List<User> findAllByEmailContainingIgnoreCase(String email) {
        return userRepository.findAllByEmailContainingIgnoreCase(email);
    }

    public List<User> findAllByEmailLikeIgnoreCase(String email) {
        return userRepository.findAllByEmailLikeIgnoreCase(email);
    }

    public Boolean existsByEmailAndPassword(String email, String password) {
        return userRepository.existsByEmailAndPassword(email, password);
    }

    public Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCaseAndPassword(email, password);
        if (optionalUser.isPresent()) {
            return optionalUser;
        } else {
            throw new RuntimeException("boyle bir kullanici bulunamadi");
        }
    }

    public List<User> passwordLongerThan(int length) {
        return userRepository.passwordLongerThan(length);
    }

    public Optional<User> findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser;
        } else {
            throw new RuntimeException("Kullanici bulunamadi");
        }
    }

    public UserRegisterResponseDto register(UserRegisterRequestDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        userRepository.save(user);
        return UserRegisterResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .userType(user.getUserType())
                .build();
    }

    public UserRegisterResponseDto register2(@RequestBody UserRegisterRequestDto dto) {
        User user = IUserMapper.INSTANCE.toUser(dto);
        userRepository.save(user);
        return IUserMapper.INSTANCE.toUserRegisterResponseDto(user);
    }

    public List<UserFindAllResponseDto> findAllDto() {
        return userRepository.findAll().stream()
                .map(x -> {
                    return UserFindAllResponseDto.builder()
                            .id(x.getId())
                            .name(x.getName())
                            .surname(x.getSurname())
                            .email(x.getEmail())
                            .userType(x.getUserType())
                            .phone(x.getPhone())
                            .favGenres(x.getFavGenres())
                            .movieCommentContents(x.getComments().stream()
                                    .map(y -> y.getContent())
                                    .collect(Collectors.toList()))
                            .movieContent(x.getComments().stream()
                                    .collect(Collectors.toMap(z -> z.getMovie().getName(), t -> t.getContent())))
                            .build();
                }).collect(Collectors.toList());
    }

    public UserLoginResponseDto loginDto(UserLoginRequestDto dto) {
        Optional<User> userOptional = userRepository.findByEmailIgnoreCaseAndPassword(dto.getEmail(), dto.getPassword());
        if(userOptional.isPresent()){
            return IUserMapper.INSTANCE.toUserLoginResponseDto(userOptional.get());
//            return UserLoginResponseDto.builder()
//                    .id(userOptional.get().getId())
//                    .name(userOptional.get().getName())
//                    .email(userOptional.get().getEmail())
//                    .userType(userOptional.get().getUserType())
//                    .build();
        }else{
            throw new MovieAppException(ErrorType.USER_NOT_FOUND);
        }
    }

    public User loginNormal(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmailIgnoreCaseAndPassword(email,password);
        if(userOptional.isEmpty()){
            throw new RuntimeException("kullanici bulunamadi");
        }else{
            return userOptional.get();
        }
    }
}

