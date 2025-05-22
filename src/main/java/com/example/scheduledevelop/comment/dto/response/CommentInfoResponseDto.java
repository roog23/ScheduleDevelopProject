package com.example.scheduledevelop.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CommentInfoResponseDto {

    //댓글 식별자
    private final Long commentId;

    //댓글 내용
    private final String comment;

    //댓글 작성자의 식별자
    private final Long userId;

    //댓글 작성한 일정의 식별자
    private final Long scheduleId;

    //댓글 작성일
    private final LocalDate createAt;

    //댓글 마지막 수정일
    private final LocalDate updateAt;
}
