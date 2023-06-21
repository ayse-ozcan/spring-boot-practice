package com.ayseozcan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieCommentSaveRequestDto {
    private String content;
    private Long userId;
    private Long movieId;
}
