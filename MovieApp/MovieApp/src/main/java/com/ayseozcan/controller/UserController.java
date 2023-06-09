package com.ayseozcan.controller;

import com.ayseozcan.dto.request.UserLoginRequestDto;
import com.ayseozcan.dto.request.UserRegisterRequestDto;
import com.ayseozcan.dto.response.UserFindAllResponseDto;
import com.ayseozcan.dto.response.UserLoginResponseDto;
import com.ayseozcan.dto.response.UserRegisterResponseDto;
import com.ayseozcan.exception.ErrorType;
import com.ayseozcan.exception.MovieAppException;
import com.ayseozcan.repository.entity.User;
import com.ayseozcan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/saveuser")
    public User createUser(String name, String surname, String email, String phone, String password, String userType) {
        return userService.createUser(name, surname, email, phone, password, userType);
    }

    @GetMapping("/findall")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/orderbyname")
    public ResponseEntity<List<User>> findAllByOrderByName() {
        return ResponseEntity.ok(userService.findAllByOrderByName());
    }

    @GetMapping("/containing")
    public ResponseEntity<List<User>> findAllByNameContaining(String name) {
        return ResponseEntity.ok(userService.findAllByNameContaining(name));
        //like kullansaydim % + name + % seklinde yazmaliydim.
    }

    @GetMapping("/emailcontains")
    public ResponseEntity<List<User>> findAllByEmailContainingIgnoreCase(String email) {
        return ResponseEntity.ok(userService.findAllByEmailContainingIgnoreCase(email));
    }

    @GetMapping("/emaillike")
    public ResponseEntity<List<User>> findAllByEmailLikeIgnoreCase(String email) {
        return ResponseEntity.ok(userService.findAllByEmailLikeIgnoreCase("%" + email));
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> existsByEmailAndPassword(String email, String password) {
        return ResponseEntity.ok(userService.existsByEmailAndPassword(email, password));
    }

    @GetMapping("/loginuser")
    public ResponseEntity<Optional<User>> findByEmailIgnoreCaseAndPassword(String email, String password) {
        return ResponseEntity.ok(userService.findByEmailIgnoreCaseAndPassword(email, password));
    }

    @GetMapping("/passwordcheck")
    public List<User> passwordLongerThan(int length) {
        return userService.passwordLongerThan(length);
        // http://localhost:9090/user/passwordcheck?length=3
    }

    @PostMapping("/register")
    public UserRegisterResponseDto register(UserRegisterRequestDto dto) {
        return userService.register(dto);
    }

    @PostMapping("/registerdto")
    public ResponseEntity<UserRegisterResponseDto> register2(UserRegisterRequestDto dto) {
        try{
            UserRegisterResponseDto responseDto = userService.register2(dto);
            System.out.println(0/100 + 100/0);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        }catch (Exception ex){
            throw new MovieAppException(ErrorType.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findalldto")
    public List<UserFindAllResponseDto> findAllDto() {
        return userService.findAllDto();
    }

    @PostMapping("/logindto")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto dto) {
        return userService.loginDto(dto);
    }

    @GetMapping("/loginnormal")
    public User loginNormal(String email, String password) {
        return userService.loginNormal(email, password);
    }
}
