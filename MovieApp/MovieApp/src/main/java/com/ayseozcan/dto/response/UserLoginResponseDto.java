package com.ayseozcan.dto.response;

import com.ayseozcan.repository.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginResponseDto {
    private Long id;
    private String name;
    private String email;
    private UserType userType;
}
