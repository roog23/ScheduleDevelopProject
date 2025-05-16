package com.example.scheduledevelop.users.controller;

import com.example.scheduledevelop.users.dto.requestDto.CreateUserRequestDto;
import com.example.scheduledevelop.users.dto.requestDto.DeleteUserRequestDto;
import com.example.scheduledevelop.users.dto.requestDto.UpdateUserRequestDto;
import com.example.scheduledevelop.users.dto.responseDto.UserInfoResponseDto;
import com.example.scheduledevelop.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserInfoResponseDto> create(@RequestBody CreateUserRequestDto requestDto) {
        UserInfoResponseDto userInfoResponseDto = userService.create(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponseDto> userFindById(@PathVariable Long id) {
        UserInfoResponseDto userInfoResponseDto = userService.userFindById(id);
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserInfoResponseDto> userUpdate(@PathVariable Long id, @RequestBody UpdateUserRequestDto requestDto) {
        UserInfoResponseDto userInfoResponseDto = userService.userUpdate(id, requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> userDelete(@PathVariable Long id, @RequestBody DeleteUserRequestDto requestDto) {
        userService.deleteUser(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
