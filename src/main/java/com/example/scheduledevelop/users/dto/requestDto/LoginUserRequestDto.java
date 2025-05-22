package com.example.scheduledevelop.users.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginUserRequestDto {

    //유저의 이메일 (이메일 형식으로 필수 작성)
    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    //유저의 비밀번호 (영어 알파벳과 숫자로 조합된 4 ~ 10자 이내로 필수 작성)
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 4, max = 10, message = "비밀번호는 4 ~ 10자 이내입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 영어 알파벳과 숫자만 입력할 수 있습니다.")
    private String password;
}
