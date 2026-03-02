package com.example.demo.service;


import com.example.demo.model.dto.UserAddRequest;
import com.example.demo.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto AddUser(UserAddRequest request);
    List<UserDto> getAllUser();
    UserDto getUserById(String id);
    UserDto UpdateUser(String id, UserAddRequest request);
    void DeleteUser(String id);
}