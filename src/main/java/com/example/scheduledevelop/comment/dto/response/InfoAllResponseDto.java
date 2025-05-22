package com.example.scheduledevelop.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InfoAllResponseDto {

    //댓글 작성자 이름
    private final String userName;

    //댓글 작성자 이메일
    private final String userMail;

    //할일 식별자
    private final Long scheduleId;

    //할일 제목
    private final String scheduleTitle;

    //할일 내용
    private final String scheduleText;

    //댓글 식별자
    private final Long commentId;

    //댓글 내용
    private final String comment;

    //댓글 생성일
    private final LocalDate createAt;

    //댓글 마지막 수정일
    private final LocalDate updateAt;
}
