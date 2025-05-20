package com.example.scheduledevelop.schedules.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SchedulePageInfoResponseDto {
    private final String title;
    private final String text;
    private final int cntComment;
    private final LocalDate createAt;
    private final LocalDate updateAt;
    private final String userName;
}
