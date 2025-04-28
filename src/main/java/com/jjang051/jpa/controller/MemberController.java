package com.jjang051.jpa.controller;

import com.jjang051.jpa.dto.MemberDto;
import com.jjang051.jpa.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {


    private final MemberService memberService;



    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/member/signup";
    }

    @PostMapping("/signup")
//    @ResponseBody
    public String signup(@Valid
                         @ModelAttribute
                         MemberDto memberDto,
                         BindingResult bindingResult
//                         Model model
    ) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("memberDto", memberDto);
//            return "/member/signup";
//        }
        int result = memberService.signUp(memberDto);
        if (result > 0) {
            return "redirect:/member/login";
        }
        return "/member/signup";

//        int result = memberService.signUp(memberDto);
//        if (result > 0) {
//            return "/member/login";
//        }
//        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "/member/login";
    }
}