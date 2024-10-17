package com.example.app.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.app.domain.dto.UserDto;

@Mapper
public interface UserMapper {

	@Insert("insert into user values(#{username},#{password},'ROLE_USER')")
	public void Insert(UserDto userDto);
	
	@Select("select * from user where username=#{username}")
	public UserDto SelectOne(String username);
	
}
