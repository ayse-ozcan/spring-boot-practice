package com.ayseozcan.repository;

import com.ayseozcan.dto.request.UserLoginRequestDto;
import com.ayseozcan.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    List<User> findAllByOrderByName();
    List<User> findAllByNameContaining(String name);
    List<User> findAllByEmailContainingIgnoreCase(String email);
    //List<User> findUsersByEmailContainingIgnoreCase(String email);
    List<User> findAllByEmailLikeIgnoreCase(String email);
    //List<User> findAllByEmailEndingWithIgnoreCase(String email);
    Boolean existsByEmailAndPassword(String email, String password);
    Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);
    @Query("select u from User u where length(u.password) > :x")
    List<User> passwordLongerThan(@Param("x") int length);
    @Query("select u from User u where length(u.password) > :x and length(u.surname) < :y")
    List<User> passwordLongerThan(@Param("x") int length, @Param("y") int length1);
    @Query(value = "select * from tbluser where length(password) > ?1",nativeQuery = true)
    List<User> passwordLongerThanNative(int length);

}
