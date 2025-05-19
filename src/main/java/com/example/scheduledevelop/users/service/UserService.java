package com.example.scheduledevelop.users.service;

import com.example.scheduledevelop.users.dto.responseDto.UserInfoResponseDto;

public interface UserService {
    UserInfoResponseDto create(String username, String password, String email);

    UserInfoResponseDto login(String email, String password);

    UserInfoResponseDto userFindById(Long id);

    UserInfoResponseDto userUpdate(Long id, String username, String password, String email);

    void deleteUser(Long id, String password);
}
