package com.example.scheduledevelop.users.service;

import com.example.scheduledevelop.users.dto.responseDto.UserInfoResponseDto;

/**
 * 유저 정보를 관리하는 서비스 인터페이스입니다.
 */
public interface UserService {
    /**
     * 유저를 생성합니다.
     * @param username  생성할 유저의 이름
     * @param password  생성할 유저의 비밀번호
     * @param email     생성할 유저의 이메일
     * @return          생성된 유저의 정보
     */
    UserInfoResponseDto userCreate(String username, String password, String email);

    /**
     * 유저 로그인 기능을 수행합니다.
     * @param email     로그인을 요청하는 유저의 이메일
     * @param password  로그인을 요청하는 유저의 비밀번호
     * @return          로그인 된 유저의 정보
     */
    UserInfoResponseDto userLogin(String email, String password);

    /**
     * 유저 식별자를 통한 유저를 찾습니다.
     * @param userId    찾길 원하는 유저의 식별자
     * @return          찾은 해당 유저의 정보
     */
    UserInfoResponseDto userFindById(Long userId);

    /**
     * 유저 정보를 수정합니다.
     * @param userId    수정을 원하는 유저의 식별자
     * @param username  수정할 이름
     * @param password  입력받은 비밀번호
     * @param email     수정할 메일
     * @return          수정된 유저의 정보
     */
    UserInfoResponseDto userUpdate(Long userId, String username, String password, String email);

    /**
     * 유저 정보를 삭제합니다.
     * @param userId    삭제를 원하는 유저의 식별자
     * @param password  입력받은 비밀번호
     */
    void userDelete(Long userId, String password);
}
