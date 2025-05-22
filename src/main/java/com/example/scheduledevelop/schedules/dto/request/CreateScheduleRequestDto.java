package com.example.scheduledevelop.schedules.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    //할일의 제목 (5 ~ 20자 이내로 필수 작성)
    @NotBlank(message = "일정 제목을 입력하세요.")
    @Size(min = 5, max = 20, message = "제목은 5 ~ 20자 이내로 입력해주세요")
    private String title;

    //할일의 내용 (5 ~ 200자 이내로 필수 작성)
    @NotBlank(message = "일정을 입력하세요.")
    @Size(min = 5, max = 200, message = "일정은 5 ~ 200자 이내로 입력해주세요")
    private String text;
}
