package com.ll.medium.domain.home.home.controller;

import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final MemberService memberService;
    @GetMapping("/") // 공개된 전체 글 리스트
    public String showMain(Model model, @RequestParam(value = "page", defaultValue = "0")int page){
        Page<Post> paging = postService.getLatestPosts();
        model.addAttribute("paging", paging);
        return "domain/home/home/main";
    }
}
