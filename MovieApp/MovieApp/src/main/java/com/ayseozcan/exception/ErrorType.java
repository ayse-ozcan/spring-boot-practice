package com.ayseozcan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(1000, "sunucuda bilinmeyen bir hata olustu", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001, "istek formati hatali", HttpStatus.BAD_REQUEST),
    REGISTER_KULLANICIADI_KAYITLI(1002, "kullanici adi kayitli", HttpStatus.BAD_REQUEST),
    NAME_IS_NULL(1003, "name alani bos birakilamaz", HttpStatus.BAD_REQUEST),
    ID_NOT_FOUND(1004, "aradiginiz id ye ait kayit bulunamadi", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1005, "gecersiz token", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4001, "Boyle bir kullanici bulunamamistir", HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatus httpStatus;
}