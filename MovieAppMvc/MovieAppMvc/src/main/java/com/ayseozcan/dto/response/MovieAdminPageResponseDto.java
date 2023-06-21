package com.ayseozcan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieAdminPageResponseDto {
    private Long id;
    private String name;
    private String language;
    private LocalDate premiered;
}
