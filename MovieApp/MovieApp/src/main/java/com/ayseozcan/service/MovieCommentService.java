package com.ayseozcan.service;

import com.ayseozcan.repository.IMovieCommentRepository;
import com.ayseozcan.repository.entity.MovieComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieCommentService {
    private final IMovieCommentRepository movieCommentRepository;
    public List<MovieComment> findAllContentByMovieId(Long id){
        if(movieCommentRepository.findByMovie_Id(id).isEmpty()){
            throw new RuntimeException("bu id ye ait film yoktur");
        }else{
            return movieCommentRepository.findByMovie_Id(id);
        }
    }
    public List<MovieComment> findAllByDateBetweenAndMovie_Id(LocalDate fdate, LocalDate ldate,Long id){
        return movieCommentRepository.findAllByDateBetweenAndMovie_Id(fdate,ldate,id);
    }
    public List<MovieComment> findAllContentByUserId(Long id){
        if(movieCommentRepository.findByUser_Id(id).isEmpty()){
            throw new RuntimeException("bu id ye ait kullanici ya da yorum yoktur");
        }else{
            return movieCommentRepository.findByUser_Id(id);
        }
    }
    public List<MovieComment> findByContentContaining(String value){
        return movieCommentRepository.findByContentContaining(value);
    }
    public List<MovieComment> findByContentGreaterThan(int value){
        return movieCommentRepository.findByContentGreaterThan(value);
    }
}
