package com.ll.medium.domain.post.post.controller;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.form.PostForm;
import com.ll.medium.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String postWrite(@Valid PostForm postForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "domain/post/post/write";
        }
        postService.write(postForm.getTitle(),postForm.getBody());
        return "redirect:/post/list";
    }
}
