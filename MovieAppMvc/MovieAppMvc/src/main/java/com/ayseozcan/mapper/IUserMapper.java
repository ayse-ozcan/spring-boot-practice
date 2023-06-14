package com.ayseozcan.mapper;

import com.ayseozcan.dto.request.UserLoginRequestDto;
import com.ayseozcan.dto.request.UserRegisterRequestDto;
import com.ayseozcan.dto.response.UserLoginResponseDto;
import com.ayseozcan.dto.response.UserRegisterResponseDto;
import com.ayseozcan.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toUser(UserRegisterRequestDto dto);

    UserRegisterResponseDto toRegisterResponseDto(final User user);
    User toUser(UserLoginRequestDto dto);
    UserLoginResponseDto toLoginResponseDto(final User user);
}
