package com.example.scheduledevelop.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CommentInfoResponseDto {
    private final Long id;
    private final String comment;
    private final Long userId;
    private final Long scheduleId;
    private final LocalDate createAt;
    private final LocalDate updateAt;
}
