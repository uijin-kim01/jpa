package com.jjang051.jpa.controller;

import com.jjang051.jpa.dto.CommentDto;
import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Comment;
import com.jjang051.jpa.entity.Question;
import com.jjang051.jpa.service.CommentService;
import com.jjang051.jpa.service.QuestionService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final QuestionService questionService;

//    @PostMapping("/write/{id}")
//    public String write(@PathVariable("id") int id,
//                        @RequestParam String content,
//                        @AuthenticationPrincipal CustomUserDetails customuserDetails
//    ) {
//        Question question = questionService.view(id);
//        commentService.write(content, question);
//        return "redirect:/question/view/" + id;
//    }

    @PostMapping("/write-ajax/{id}")
    @ResponseBody
    public Map<String, Object> writeAjax(
            @PathVariable("id") int id,
            @RequestBody CommentDto contentDto,
            @AuthenticationPrincipal  CustomUserDetails customUserDetails ) {

        Question question = questionService.view(id);
        Comment comment = commentService.write(contentDto.getContent(), customUserDetails.getLoggedMember(), question);
        Map<String, Object> result = new HashMap<>();
        if (comment != null) {
            CommentDto responseCommentDto = CommentDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .writer(comment.getWriter().getUserName())
                    .regDate(comment.getRegDate())
                    .build();
            result.put("comment", responseCommentDto);
        }
        return result;
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteComment(@PathVariable Integer id) {
        commentService.delete(id);  // 예외를 따로 처리하지 않음
        Map<String, Object> response = new HashMap<>();
        response.put("isCommentDelete", true);
        return response;
    }


}
