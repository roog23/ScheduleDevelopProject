package com.example.scheduledevelop.schedules.service;


import com.example.scheduledevelop.schedules.dto.response.ScheduleInfoResponseDto;

public interface ScheduleService {
    ScheduleInfoResponseDto create(Long id, String title, String text);

    ScheduleInfoResponseDto scheduleFindById(Long id);

    void delete(Long id, Long userId, String password);

    ScheduleInfoResponseDto scheduleUpdate(Long id, Long userId, String password,String title, String text);
}
