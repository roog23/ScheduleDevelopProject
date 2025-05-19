package com.example.scheduledevelop.schedules.controller;


import com.example.scheduledevelop.schedules.dto.request.CreateScheduleRequestDto;
import com.example.scheduledevelop.schedules.dto.request.DeleteScheduleRequestDto;
import com.example.scheduledevelop.schedules.dto.request.UpdateScheduleRequestDto;
import com.example.scheduledevelop.schedules.dto.response.ScheduleInfoResponseDto;
import com.example.scheduledevelop.schedules.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    public final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleInfoResponseDto> scheduleCreate(@RequestBody CreateScheduleRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        ScheduleInfoResponseDto responseDto = scheduleService.create(userId, requestDto.getTitle(), requestDto.getText());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleInfoResponseDto> scheduleFindById(@PathVariable Long id) {
        ScheduleInfoResponseDto responseDto = scheduleService.scheduleFindById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleInfoResponseDto> scheduleUpdate(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        ScheduleInfoResponseDto responseDto = scheduleService.scheduleUpdate(id, userId, requestDto.getPassword(), requestDto.getTitle(), requestDto.getText());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> scheduleDelete(@PathVariable Long id, @RequestBody DeleteScheduleRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        scheduleService.delete(id, userId, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
