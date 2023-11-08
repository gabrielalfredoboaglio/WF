package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.user.EditUserDto;
import com.C10G14.WorldFitBackend.dto.user.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.user.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
    UserDto getUserById (Long id);
    SimpleUserDto getSimpleUserById (Long id);
    UserDto createUser (UserDto userDto);
    UserDto updateUser(Long id, EditUserDto userDto);
    UserDto updateRole(Long id, String requestRole);
    void deleteUser (Long id);
    List<UserDto> getByRole(String requestRole);
}
