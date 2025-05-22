package com.example.scheduledevelop.schedules.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DeleteScheduleRequestDto {

    //작성자의 비밀번호 (영어 알파벳과 숫자로 조합된 4 ~ 10자 이내로 필수 작성)
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 4, max = 10, message = "비밀번호는 4 ~ 10자 이내입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 영어 알파벳과 숫자만 입력할 수 있습니다.")
    private String password;
}
