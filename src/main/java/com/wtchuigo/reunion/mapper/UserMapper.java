package com.wtchuigo.reunion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wtchuigo.reunion.core.UserDto;
import com.wtchuigo.reunion.model.User;

@Mapper
@SuppressWarnings("java:S106")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDto userToUserDto(User user);

}
