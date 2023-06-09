package com.ayseozcan.controller;

import com.ayseozcan.repository.entity.MovieComment;
import com.ayseozcan.service.MovieCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/moviecomment")
@RequiredArgsConstructor
public class MovieCommentController {
    private final MovieCommentService movieCommentService;
    @GetMapping("/findcontent")
    public ResponseEntity<List<MovieComment>> findAllContentByMovieId(Long id){
        return ResponseEntity.ok(movieCommentService.findAllContentByMovieId(id));
    }
    @GetMapping("/findcontentbetweendate")
    public ResponseEntity<List<MovieComment>> findAllByDateBetweenAndMovie_Id(LocalDate fdate, LocalDate ldate,Long id){
        return ResponseEntity.ok(movieCommentService.findAllByDateBetweenAndMovie_Id(fdate,ldate,id));
    }

    @GetMapping("/findcontentuser")
    public ResponseEntity<List<MovieComment>> findAllContentByUserId(Long id){
        return ResponseEntity.ok(movieCommentService.findAllContentByUserId(id));
    }
    @GetMapping("/findcontentcontains")
    public ResponseEntity<List<MovieComment>> findByContentContaining(String value){
        return ResponseEntity.ok(movieCommentService.findByContentContaining(value));
    }
    @GetMapping("/findcontentgreaterthan")
    public ResponseEntity<List<MovieComment>> findByContentGreaterThan(int value){
        return ResponseEntity.ok(movieCommentService.findByContentGreaterThan(value));
    }
}
