package com.example.scheduledevelop.users.controller;

import com.example.scheduledevelop.users.dto.requestDto.CreateUserRequestDto;
import com.example.scheduledevelop.users.dto.requestDto.DeleteUserRequestDto;
import com.example.scheduledevelop.users.dto.requestDto.LoginUserRequestDto;
import com.example.scheduledevelop.users.dto.requestDto.UpdateUserRequestDto;
import com.example.scheduledevelop.users.dto.responseDto.UserInfoResponseDto;
import com.example.scheduledevelop.users.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserInfoResponseDto> userCreate(@Valid @RequestBody CreateUserRequestDto requestDto, HttpServletRequest request) {
        UserInfoResponseDto userInfoResponseDto = userService.userCreate(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
        HttpSession session = request.getSession();
        session.setAttribute("sessionKey", userInfoResponseDto.getId());
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<UserInfoResponseDto> userLogin(@Valid @RequestBody LoginUserRequestDto requestDto, HttpServletRequest request) {
        UserInfoResponseDto userInfoResponseDto = userService.userLogin(requestDto.getEmail(), requestDto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("sessionKey", userInfoResponseDto.getId());
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponseDto> userFindById(@PathVariable Long id) {
        UserInfoResponseDto userInfoResponseDto = userService.userFindById(id);
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserInfoResponseDto> userUpdate(@Valid @RequestBody UpdateUserRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("sessionKey");
        UserInfoResponseDto userInfoResponseDto = userService.userUpdate(id, requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> userDelete(@Valid @RequestBody DeleteUserRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("sessionKey");
        userService.userDelete(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
