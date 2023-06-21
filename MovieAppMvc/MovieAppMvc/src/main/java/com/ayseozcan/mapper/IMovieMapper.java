package com.ayseozcan.mapper;

import com.ayseozcan.dto.response.MovieAdminPageResponseDto;
import com.ayseozcan.repository.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMovieMapper {
    IMovieMapper INSTANCE = Mappers.getMapper(IMovieMapper.class);
    List<MovieAdminPageResponseDto> toMovieAdminPageResponseDto(List<Movie> movieList);
}
