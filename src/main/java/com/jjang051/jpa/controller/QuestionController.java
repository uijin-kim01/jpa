package com.jjang051.jpa.controller;

import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.dto.QuestionDto;
import com.jjang051.jpa.entity.Question;
import com.jjang051.jpa.repository.QuestionRepository;
import com.jjang051.jpa.service.CommentService;
import com.jjang051.jpa.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final CommentService commentService;


    @GetMapping("/write")
    public String write() {
        return "question/write";
    }

    @PostMapping("/write")
    //@ResponseBody
    public String write(@ModelAttribute QuestionDto questionDto,
                        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        questionDto.setWriter(customUserDetails.getLoggedMember());
        questionService.write(questionDto);
        return "redirect:../question/list";
    }





    @GetMapping("/list")
    //@ResponseBody
    public String list(Model model) {
        List<Question> list = questionService.list();
        model.addAttribute("list", list);
        return "question/list";
    }





    @GetMapping("/view/{id}")
//    @ResponseBody
    public String view(@PathVariable("id") Integer id, Model model) {
        //front에서 넘어오는거는 dto로 받아서 service로 던져주면 entity로바꿔서 저장
        //db애서 조회된 entity는 dto로 바꿔서 front에 전달한다.
        // FRONT -> DTO -> SERVICE -> ENTITY -> DB 로 던진다
        // DB에서 나온 값은 ENTITY기 때문에 DTO로 바꿔서 다시 FRONT에서 출력 가능하게 변경.
        Question question = questionService.view(id);
        QuestionDto questionDto = null;
        if (question != null) {
//            questionDto = QuestionDto.builder()
//                    .id(question.getId())
//                    .title(question.getTitle())
//                    .content(question.getContent())
//                    .regDate(question.getRegDate())
//                    .build();
            questionDto = Question.toDto(question);
        }
        model.addAttribute("questionDto", questionDto);
//        log.info(questionDto.getCommentList().get(0).getContent());
        return "question/view";
    }





    @GetMapping("/modify/{id}")
    //@ResponseBody
    public String modify(@PathVariable("id") Integer id, Model model) {
        Question question = questionService.view(id);
        QuestionDto questionDto = null;
        if (question != null) {
            questionDto = Question.toDto(question);
        }
        model.addAttribute("questionDto", questionDto);
        log.info(questionDto.toString());
        return "question/modify";
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute QuestionDto questionDto) {

        Question question = questionService.modify(questionDto);

        log.info("question == {} " , question);

        if (question != null) {
            return "redirect:/question/list";
        }
        return "question/modify/"+questionDto.getId();
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id) {
        commentService.delete(id);
        Map<String, Object> result = new HashMap<>();
        result.put("isCommentDelete", true);
        return result;
    }
}
