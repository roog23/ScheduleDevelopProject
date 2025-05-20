package com.example.scheduledevelop.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    @Size(max = 20, message = "댓글은 20자 이내로 작성해주세요.")
    private String comment;
}
