package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.entity.Question;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@ToString
@Builder
@AllArgsConstructor
public class CommentDto {
    private int id;
    private String content;
    private Question question;
    private LocalDateTime regDate;
    private LocalDateTime modifyDate;
    private String writer;
    private Member member;
}
