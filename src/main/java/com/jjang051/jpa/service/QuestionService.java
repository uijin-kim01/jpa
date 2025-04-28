package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.QuestionDto;
import com.jjang051.jpa.entity.Question;
import com.jjang051.jpa.repository.CommentRepository;
import com.jjang051.jpa.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;

    public void write(QuestionDto questionDto) {
//        Question question = Question.builder()
//                .title(questionDto.getTitle())
//                .content(questionDto.getContent())
//                            .build();
        Question question = QuestionDto.toEntity(questionDto);  //jpa  entity
        questionRepository.save(question);
    }

    public List<Question> list() {
        return questionRepository.findAll();
    }

    public Question view(Integer id) {
        Optional<Question> optionalQuestion =
                questionRepository.findById(id);
        if (optionalQuestion.isPresent())
            return optionalQuestion.get();
        return null;
    }

//    public Question modify(QuestionDto questionDto) {

    //        Optional<Question> optionalQuestion = questionRepository.findById(questionDto.getId());
//        if (optionalQuestion.isPresent()) {
//            questionRepository.save(QuestionDto.toEntity(questionDto));
//        }
//        return null;
//}
    @Transactional // service에서만 사용 가능한 annotation
    public Question modify(QuestionDto questionDto) {
        Question question = questionRepository.findById(questionDto.getId()).orElseThrow();
        question.changeTitle(questionDto.getTitle().trim());
        question.changeContent(questionDto.getContent().trim());

//        question.setTitle(questionDto.getTitle().trim());
//        question.setContent(questionDto.getContent().trim());

//        optionalQuestion.ifPresent(question -> {
//            Question updateQuestion = optionalQuestion.get();
//            updateQuestion = QuestionDto.toEntity(questionDto);
//        });
        return question;
    }
}
