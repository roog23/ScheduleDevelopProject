package com.example.scheduledevelop.schedules.service;


import com.example.scheduledevelop.schedules.dto.response.ScheduleInfoResponseDto;
import com.example.scheduledevelop.schedules.dto.response.SchedulePageInfoResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleInfoResponseDto scheduleCreate(Long id, String title, String text);

    ScheduleInfoResponseDto scheduleFindById(Long id);

    ScheduleInfoResponseDto scheduleUpdate(Long id, Long userId, String password,String title, String text);

    void scheduleDelete(Long id, Long userId, String password);

    List<SchedulePageInfoResponseDto> schedulePage(Integer pageNum, Integer pageSize);
}
