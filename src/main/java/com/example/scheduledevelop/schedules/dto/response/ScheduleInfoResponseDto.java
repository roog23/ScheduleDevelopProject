package com.example.scheduledevelop.schedules.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScheduleInfoResponseDto {
    private final Long id;
    private final String userName;
    private final String scheduleTitle;
    private final String ScheduleText;
    private final LocalDate createAt;
    private final LocalDate updateAt;
}
