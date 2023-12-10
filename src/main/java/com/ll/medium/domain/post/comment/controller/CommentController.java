package com.ll.medium.domain.post.comment.controller;

import com.ll.medium.domain.post.comment.form.CommentForm;
import com.ll.medium.domain.post.comment.service.CommentService;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/write/{id}")
    public String write(Model model, @PathVariable("id") Integer id, @Valid CommentForm commentForm, BindingResult bindingResult) {
        Post post = postService.getPost(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "domain/post/post/detail";
        }
        commentService.create(post, commentForm.getContent());
        return String.format("redirect:/post/%s", id);
    }


}
