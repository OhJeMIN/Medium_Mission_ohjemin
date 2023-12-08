package com.ll.medium.domain.post.comment.controller;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.service.PostService;
import com.ll.medium.domain.post.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService articleService;
    private final CommentService commentService;

    @PostMapping("/write/{id}")
    public String write(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
        Post post = this.articleService.getPost(id);
        commentService.create(post, content);
        return String.format("redirect:/post/%s", id);
    }
}
