package com.ayseozcan.dto.response;

import com.ayseozcan.repository.entity.Genre;
import com.ayseozcan.repository.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFindAllResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private UserType userType;
    private List<Genre> favGenres;
    private List<String> movieCommentContents;
    private Map<String, String> movieContent;
}
