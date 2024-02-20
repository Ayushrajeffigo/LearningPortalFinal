package com.effigo.learningPortal.mapper;

import org.mapstruct.Mapper;

import com.effigo.learningPortal.dto.RegisterUserDto;
import com.effigo.learningPortal.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User registerUserDtoToUser(RegisterUserDto registerUser);

	RegisterUserDto userToRegisterUserDto(User user);
}