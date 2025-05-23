package com.example.scheduledevelop.schedules.controller;


import com.example.scheduledevelop.schedules.dto.request.CreateScheduleRequestDto;
import com.example.scheduledevelop.schedules.dto.request.DeleteScheduleRequestDto;
import com.example.scheduledevelop.schedules.dto.request.UpdateScheduleRequestDto;
import com.example.scheduledevelop.schedules.dto.response.ScheduleInfoResponseDto;
import com.example.scheduledevelop.schedules.dto.response.SchedulePageInfoResponseDto;
import com.example.scheduledevelop.schedules.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.scheduledevelop.users.controller.UserController.sessionUserId;

/**
 * 할일 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    public final ScheduleService scheduleService;

    /**
     * 할일을 생성합니다.
     * @param requestDto    할일 정보를 담고 있는 요청 DTO
     * @param request       세션 키 정보 (userId)를 얻기 위해 사용
     * @return              생성된 할일 정보, HttpStatus.CREATED 응답
     */
    @PostMapping
    public ResponseEntity<ScheduleInfoResponseDto> scheduleCreate(@Valid @RequestBody CreateScheduleRequestDto requestDto, HttpServletRequest request) {
        Long userId = sessionUserId(request);
        ScheduleInfoResponseDto responseDto = scheduleService.scheduleCreate(userId, requestDto.getTitle(), requestDto.getText());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 할일 식별자를 통한 할일 조회입니다.
     * @param scheduleId    조회하고자 하는 할일 식별자
     * @return              조회된 할일 정보, HttpStatus.OK 응답
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleInfoResponseDto> scheduleFindById(@PathVariable Long scheduleId) {
        ScheduleInfoResponseDto responseDto = scheduleService.scheduleFindById(scheduleId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 할일 페이지 조회입니다.
     * @param pageNum   페이지 위치
     * @param pageSize  페이지 크기
     * @return          페이지에 해당하는 할일 정보, HttpStatus.OK 응답
     */
    @GetMapping
    public ResponseEntity<List<SchedulePageInfoResponseDto>> schedulePage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<SchedulePageInfoResponseDto> response = scheduleService.schedulePage(pageNum,pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 할일 정보를 수정합니다.
     * @param scheduleId    수정하고자 하는 할일 식별자
     * @param requestDto    수정하고자 하는 할일 제목, 할일 내용을 담고 있는 요청 DTO
     * @param request       세션 키 정보 (userId)를 얻는데 사용
     * @return              수정된 할일 정보, HttpStatus.OK 응답
     */
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleInfoResponseDto> scheduleUpdate(@PathVariable Long scheduleId, @Valid @RequestBody UpdateScheduleRequestDto requestDto, HttpServletRequest request) {
        Long userId = sessionUserId(request);
        ScheduleInfoResponseDto responseDto = scheduleService.scheduleUpdate(scheduleId, userId, requestDto.getPassword(), requestDto.getTitle(), requestDto.getText());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 할일 정보를 삭제합니다.
     * @param scheduleId    삭제를 원하는 할일 식별자
     * @param requestDto    삭제를 원하는 할일 작성자의 비밀번호를 담고 있는 요청 DTO
     * @param request       세션 키 정보 (userId)를 얻는데 사용
     * @return              HttpStatus.OK 응답
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> scheduleDelete(@PathVariable Long scheduleId, @Valid @RequestBody DeleteScheduleRequestDto requestDto, HttpServletRequest request) {
        Long userId = sessionUserId(request);
        scheduleService.scheduleDelete(scheduleId, userId, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
