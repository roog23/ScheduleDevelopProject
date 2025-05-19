package com.example.scheduledevelop.schedules.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 4, max = 10, message = "비밀번호는 4 ~ 10자 이내입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 영어 알파벳과 숫자만 입력할 수 있습니다.")
    private String password;

    @NotBlank(message = "일정 제목을 입력하세요.")
    @Size(min = 5, max = 20, message = "제목은 5 ~ 20자 이내로 입력해주세요")
    private String title;

    @NotBlank(message = "일정을 입력하세요.")
    @Size(min = 5, max = 200, message = "일정은 5 ~ 200자 이내로 입력해주세요")
    private String text;
}
