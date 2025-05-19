package com.example.scheduledevelop.users.service;

import com.example.scheduledevelop.users.dto.responseDto.UserInfoResponseDto;

public interface UserService {
    UserInfoResponseDto userCreate(String username, String password, String email);

    UserInfoResponseDto userLogin(String email, String password);

    UserInfoResponseDto userFindById(Long id);

    UserInfoResponseDto userUpdate(Long id, String username, String password, String email);

    void userDelete(Long id, String password);
}
