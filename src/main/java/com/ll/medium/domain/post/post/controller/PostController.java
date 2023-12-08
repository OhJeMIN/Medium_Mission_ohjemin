package com.ll.medium.domain.post.post.controller;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/list") // 전체 글 리스트
    public String list(Model model){
        List<Post> postList = postService.getList();
        model.addAttribute("postList", postList);
        return "domain/post/post/list";
    }

    @GetMapping(value = "/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "domain/post/post/detail";
    }

    @GetMapping("/write")
    public String getWrite() {
        return "domain/post/post/write";
    }

    @PostMapping("/write")
    public String postWrite(@RequestParam(value="title") String title, @RequestParam(value="body") String body) {
        // TODO 질문을 저장한다.
        return "redirect:domain/post/post/list";
    }
}
