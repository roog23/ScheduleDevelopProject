package com.example.scheduledevelop.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InfoAllResponseDto {

    private final String userName;
    private final String userMail;
    private final Long scheduleId;
    private final String scheduleTitle;
    private final String scheduleText;
    private final Long id;
    private final String comment;
    private final LocalDate createAt;
    private final LocalDate updateAt;
}
