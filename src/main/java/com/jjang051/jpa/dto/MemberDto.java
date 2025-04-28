package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Member;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MemberDto {

    @NotBlank(message = "아이디는 필수 입력사항입니다.")
    private String userID;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    private String userPW;

    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    private String userName;

    @NotBlank
    @Email(message = "이메일 형식에 맞게 써주세요.")
    private String userEmail;

    private int zipcode;

    private String address01;
    private String address02;

    private String tel;

//    private MultipartFile profile;

    private String originalProfile;
    private String renameProfile;
    private String roles;
    private String regdate;


    public static Member toEntity(MemberDto memberDto) {
        return Member.builder()
                .userID(memberDto.getUserID())
                .userPW(memberDto.getUserPW())
                .userName(memberDto.getUserName())
                .userEmail(memberDto.getUserEmail())
                .zipcode(memberDto.getZipcode())
                .address01(memberDto.getAddress01())
                .address02(memberDto.getAddress02())
                .tel(memberDto.getTel())
                .roles(memberDto.getRoles())
                .build();
    }
}
