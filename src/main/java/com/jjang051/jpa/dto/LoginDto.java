package com.jjang051.jpa.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotBlank(message = "아이디는 필수 입력사항입니다.")
    private String userID;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    private String userPW;

}
