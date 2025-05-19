package com.example.scheduledevelop.users.service;

import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.WrongPasswordException;
import com.example.scheduledevelop.users.dto.responseDto.UserInfoResponseDto;
import com.example.scheduledevelop.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserInfoResponseDto userCreate(String username, String password, String email) {
        String encodePassword = passwordEncoder.encode(password);
        User user = new User(username, encodePassword, email);
        User savedUser = userRepository.save(user);
        return new UserInfoResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreateAt(), savedUser.getUpdateAt());
    }

    @Override
    public UserInfoResponseDto userLogin(String email, String password) {
        User findUser = userRepository.findByEmailOrElse(email);
        checkUserPassword(findUser, password);

        return new UserInfoResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail(), findUser.getCreateAt(), findUser.getUpdateAt());
    }

    @Override
    public UserInfoResponseDto userFindById(Long id) {
        User finduser = userRepository.findByIdOrElseThrow(id);

        return new UserInfoResponseDto(finduser.getId(), finduser.getUsername(), finduser.getEmail(), finduser.getCreateAt(), finduser.getUpdateAt());
    }

    @Transactional
    @Override
    public UserInfoResponseDto userUpdate(Long id, String username, String password, String email) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        checkUserPassword(findUser, password);

        findUser.updateUser(username, email);

        return new UserInfoResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail(), findUser.getCreateAt(), findUser.getUpdateAt());
    }

    @Override
    public void userDelete(Long id, String password) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        checkUserPassword(findUser, password);

        userRepository.delete(findUser);
    }

    private void checkUserPassword(User findUser, String password) {
        if(passwordEncoder.matches(password, findUser.getPassword())) {
            throw new WrongPasswordException();
        }
    }

}
