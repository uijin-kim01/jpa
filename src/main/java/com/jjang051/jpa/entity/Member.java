package com.jjang051.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jjang051.jpa.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Table(name = "entity_member")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String userID;

    private String userPW;
    private String userName;
    private String userEmail;
    private int zipcode;
    private String address01;
    private String address02;
    private String tel;

    //    private String profile;
//    private String originalProfile;
//    private String renameProfile;
    private String roles;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questionList;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> commentList;

    @Builder
    public Member(String userID, String userPW, String userName, String userEmail, int zipcode, String address01, String address02, String tel, String roles) {
        this.userID = userID;
        this.userPW = userPW;
        this.userName = userName;
        this.userEmail = userEmail;
        this.zipcode = zipcode;
        this.address01 = address01;
        this.address02 = address02;
        this.tel = tel;
        this.roles = roles;
    }

    public static MemberDto toDto(Member member) {
        MemberDto memberDto = MemberDto.builder()
                .userID(member.getUserID())
                .userPW(member.getUserPW())
                .userName(member.getUserName())
                .userEmail(member.getUserEmail())
                .zipcode(member.getZipcode())
                .address01(member.getAddress01())
                .address02(member.getAddress02())
                .tel(member.getTel())
                .roles(member.getRoles())
                .build();
        return memberDto;
    }

//    public static Member toDto(Member member) {
//
//    }
}
