package com.ll.medium.domain.post.post.controller;

import com.ll.medium.domain.post.comment.form.CommentForm;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.form.PostForm;
import com.ll.medium.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/list") // 전체 글 리스트
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0")int page){
        Page<Post> paging = postService.getList(page);
        model.addAttribute("paging", paging);
        return "domain/post/post/list";
    }

    @GetMapping(value = "/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, CommentForm commentForm) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "domain/post/post/detail";
    }

    @GetMapping("/write")
    public String getWrite(PostForm postForm) {
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
