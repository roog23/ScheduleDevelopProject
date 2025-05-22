package com.example.scheduledevelop.schedules.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScheduleInfoResponseDto {

    //할일의 고유 식별자
    private final Long ScheduleId;

    //할일 작성자 이름
    private final String userName;

    //할일 제목
    private final String scheduleTitle;

    //할일 내용
    private final String ScheduleText;

    //할일 생성일
    private final LocalDate createAt;

    //할일 마지막 수정일
    private final LocalDate updateAt;
}
