package com.ll.medium.domain.home.home.controller;

import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class AdmHomeController {
    private final PostService postService;
    private final MemberService memberService;
    @GetMapping("") // 공개된 전체 글 리스트
    @PreAuthorize("hasRole('ADMIN')")
    public String showMain(){
        return "domain/home/home/adm/main";
    }
}
