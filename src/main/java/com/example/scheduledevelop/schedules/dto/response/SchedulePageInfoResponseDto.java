package com.example.scheduledevelop.schedules.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SchedulePageInfoResponseDto {

    //할일의 고유 식별자
    private final Long scheduleId;

    //할일 제목
    private final String title;

    //할일 내용
    private final String text;

    //할일에 달린 댓글수
    private final int cntComment;

    //할일 생성일
    private final LocalDate createAt;

    //할일 수정일
    private final LocalDate updateAt;

    //할일 작성자 이름
    private final String userName;
}
