package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Comment;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.entity.Question;
import com.jjang051.jpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment write(String content, Member writer, Question question) {
//        commentRepository.save()

        Comment comment = Comment.builder()
                .content(content)
                .question(question)
                .writer(writer)
                .build();
        return commentRepository.save(comment);
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }
}
