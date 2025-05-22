package com.example.scheduledevelop.users.dto.responseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class UserInfoResponseDto {

    //유저의 고유 식별자
    private final Long userId;

    //유저의 이름
    private final String username;

    //유저의 이메일
    private final String email;

    //유저 생성일
    private final LocalDate createAt;

    //유저의 마지막 수정일
    private final LocalDate updateAt;
}
