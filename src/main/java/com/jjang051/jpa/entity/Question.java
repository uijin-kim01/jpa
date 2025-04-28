package com.jjang051.jpa.entity;

import com.jjang051.jpa.dto.QuestionDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
//@Setter
@NoArgsConstructor
//@Table(name="table_question")  //table에 이름 바꾸고 싶을때...
//@EntityListeners(AuditingEntityListener.class)
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private Member writer;

    @Builder
    public Question(Integer id, String title, String content,
                    LocalDateTime regDate, Member writer, LocalDateTime modifyDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
        this.modifyDate = modifyDate;
    }


    public static QuestionDto toDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .regDate(question.getRegDate())
                .writer(question.getWriter())
                .modifyDate(question.getModifyDate())
                .commentList(question.getCommentList())
                .build();

    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
