package com.ayseozcan.service;

import com.ayseozcan.dto.request.MovieCommentSaveRequestDto;
import com.ayseozcan.repository.IMovieCommentRepository;
import com.ayseozcan.repository.entity.MovieComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MovieCommentService {
    private final IMovieCommentRepository movieCommentRepository;
    private final UserService userService;
    private final MovieService movieService;

    public void save(MovieCommentSaveRequestDto dto) {
        MovieComment movieComment = MovieComment.builder()
                .content(dto.getContent())
                .user(userService.findById(dto.getUserId()).get())
                .movie(movieService.findbyId(dto.getMovieId()))
                .build();
        movieCommentRepository.save(movieComment);

        /**
        MovieComment movieComment= IMovieCommentMapper.INSTANCE.toMovieComment(dto);
        movieComment.setMovie(movie);
        movieComment.setUser(user);
         */
    }
}
