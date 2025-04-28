package com.jjang051.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionNum")
    @JsonIgnore
    private Question question;

    @Builder
    public Comment(Integer id, String content, Question question, Member writer) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.question = question;
    }
}
