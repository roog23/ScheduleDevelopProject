package com.example.scheduledevelop.users.service;

import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.WrongPasswordException;
import com.example.scheduledevelop.users.dto.responseDto.UserInfoResponseDto;
import com.example.scheduledevelop.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 유저 정보를 관리하는 서비스 구현체입니다.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 유저를 생성합니다.
     * @param username  생성할 유저의 이름
     * @param password  생성할 유저의 비밀번호
     * @param email     생성할 유저의 이메일
     * @return          생성된 유저의 정보
     */
    @Override
    public UserInfoResponseDto userCreate(String username, String password, String email) {
        String encodePassword = passwordEncoder.encode(password);
        User user = new User(username, encodePassword, email);
        User savedUser = userRepository.save(user);
        return new UserInfoResponseDto(savedUser.getUserId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreateAt(), savedUser.getUpdateAt());
    }

    /**
     * 유저 로그인 기능을 수행합니다.
     * @param email     로그인을 요청하는 유저의 이메일
     * @param password  로그인을 요청하는 유저의 비밀번호
     * @return          로그인 된 유저의 정보
     */
    @Override
    public UserInfoResponseDto userLogin(String email, String password) {
        User findUser = userRepository.findByEmailOrElse(email);
        checkUserPassword(findUser, password);

        return new UserInfoResponseDto(findUser.getUserId(), findUser.getUsername(), findUser.getEmail(), findUser.getCreateAt(), findUser.getUpdateAt());
    }

    /**
     * 유저 식별자를 통한 유저를 조회합니다.
     * @param userId 찾길 원하는 유저의 식별자
     * @return       찾은 해당 유저의 정보
     */
    @Override
    public UserInfoResponseDto userFindById(Long userId) {
        User finduser = userRepository.findByIdOrElseThrow(userId);

        return new UserInfoResponseDto(finduser.getUserId(), finduser.getUsername(), finduser.getEmail(), finduser.getCreateAt(), finduser.getUpdateAt());
    }

    /**
     * 유저 정보를 수정합니다.
     * @param userId    수정을 원하는 유저의 식별자
     * @param username  수정할 이름
     * @param password  입력받은 비밀번호
     * @param email     수정할 메일
     * @return          수정된 유저의 정보
     */
    @Transactional
    @Override
    public UserInfoResponseDto userUpdate(Long userId, String username, String password, String email) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        checkUserPassword(findUser, password);

        findUser.updateUser(username, email);

        return new UserInfoResponseDto(findUser.getUserId(), findUser.getUsername(), findUser.getEmail(), findUser.getCreateAt(), findUser.getUpdateAt());
    }

    /**
     * 유저 정보를 삭제합니다.
     * @param userId    삭제를 원하는 유저의 식별자
     * @param password  입력받은 비밀번호
     */
    @Transactional
    @Override
    public void userDelete(Long userId, String password) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        checkUserPassword(findUser, password);

        userRepository.delete(findUser);
    }

    /**
     * 유저의 비밀번호가 입력받은 비밀번호와 일치하는지 확인합니다.
     * @param findUser  저장되어 있는 유저의 정보
     * @param password  입력받은 비밀번호
     */
    private void checkUserPassword(User findUser, String password) {
        //입력받은 비밀번호가 등록된 비밀번호와 일치하는지 확인해줍니다.
        //비밀번호가 틀린 경우 true를 반환합니다.
        if(passwordEncoder.matches(password, findUser.getPassword())) {
            throw new WrongPasswordException();
        }
    }

}
