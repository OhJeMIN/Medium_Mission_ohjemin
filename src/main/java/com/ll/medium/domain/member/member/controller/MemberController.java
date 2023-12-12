package com.ll.medium.domain.member.member.controller;

import com.ll.medium.domain.member.member.form.MemberForm;
import com.ll.medium.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    final private MemberService memberService;

    @GetMapping("/join")
    public String join(MemberForm memberForm){
        return "domain/member/member/join";
    }
}
