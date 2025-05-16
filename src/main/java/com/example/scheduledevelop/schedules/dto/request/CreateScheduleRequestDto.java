package com.example.scheduledevelop.schedules.dto.request;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {
    private Long userId;
    private String title;
    private String text;
}
