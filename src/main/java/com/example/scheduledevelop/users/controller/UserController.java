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

/**
 * 유저 요청을 처리하는 컨트롤러입니다.
 */
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 유저를 생성합니다.
     * @param requestDto    유저 정보를 담고 있는 요청 DTO
     * @param request       세션 정보를 설정하는데 사용
     * @return              생성된 유저의 정보, HttpStatus.CREATED 응답
     */
    @PostMapping("/create")
    public ResponseEntity<UserInfoResponseDto> userCreate(@Valid @RequestBody CreateUserRequestDto requestDto, HttpServletRequest request) {
        UserInfoResponseDto userInfoResponseDto = userService.userCreate(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
        HttpSession session = request.getSession();
        session.setAttribute("sessionKey", userInfoResponseDto.getUserId());
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.CREATED);
    }

    /**
     * 로그인을 수행합니다.
     * @param requestDto    로그인에 필요한 이메일, 비밀번호를 담고 있는 요청 DTO
     * @param request       세션 정보를 설정하는데 사용
     * @return              로그인한 유저의 정보, HttpStatus.OK 응답
     */
    @GetMapping("/login")
    public ResponseEntity<UserInfoResponseDto> userLogin(@Valid @RequestBody LoginUserRequestDto requestDto, HttpServletRequest request) {
        UserInfoResponseDto userInfoResponseDto = userService.userLogin(requestDto.getEmail(), requestDto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("sessionKey", userInfoResponseDto.getUserId());
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    /**
     * 유저 식별자를 통한 유저 조회입니다.
     * @param userId    조회하고자 하는 유저 식별자
     * @return          조회된 유저의 정보, HttpStatus.OK 응답
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoResponseDto> userFindById(@PathVariable Long userId) {
        UserInfoResponseDto userInfoResponseDto = userService.userFindById(userId);
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    /**
     * 유저 정보를 수정합니다.
     * @param requestDto    수정하고자 하는 유저 이름, 이메일을 담고 있는 요청 DTO
     * @param request       세션 키 정보 (userId)를 얻는데 사용
     * @return              수정된 유저의 정보, HttpStatus.OK 응답
     */
    @PatchMapping
    public ResponseEntity<UserInfoResponseDto> userUpdate(@Valid @RequestBody UpdateUserRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        UserInfoResponseDto userInfoResponseDto = userService.userUpdate(userId, requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    /**
     * 유저 정보를 삭제합니다.
     * @param requestDto    삭제를 원하는 유저의 비밀번호를 담고 있는 요청 DTO
     * @param request       세션 키 정보 (userId)를 얻는데 사용
     * @return              HttpStatus.OK 응답
     */
    @DeleteMapping
    public ResponseEntity<Void> userDelete(@Valid @RequestBody DeleteUserRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        userService.userDelete(userId, requestDto.getPassword());
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
