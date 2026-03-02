package com.example.demo.service.Impl;

import com.example.demo.model.dto.UserAddRequest;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.ValidationUtil;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private ValidationUtil
 validationUtil;

    @Override
    public UserDto AddUser(UserAddRequest request) {
        validationUtil.validate(request);

        User saveUser = User.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .age(request.getAge())
                .build();

        UserRepository.save(saveUser);

        UserDto UserDto = UserMapper.MAPPER.toUserDtoData(saveUser);

        return UserDto;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> Users = UserRepository.findAll();
        List<UserDto> UserDto = new ArrayList<>();
        for (User User : Users) {
            UserDto.add(UserMapper.MAPPER.toUserDtoData(User));
        }
        return UserDto;
    }

    @Override
    public UserDto getUserById(String id) {
        User User = UserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.MAPPER.toUserDtoData(User);
    }

    @Override
    public UserDto UpdateUser(String id, UserAddRequest request) {
        validationUtil.validate(request);

        User existingUser = UserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        User user = User.builder()
                .id(existingUser.getId())
                .name(request.getName())
                .age(request.getAge())
                .build();

        UserRepository.save(user);

        return UserMapper.MAPPER.toUserDtoData(user);
    }

    @Override
    public void DeleteUser(String id) {
        User User = UserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserRepository.delete(User);
    }
}