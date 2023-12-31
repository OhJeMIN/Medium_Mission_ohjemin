package com.ll.medium.domain.post.comment.controller;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.domain.post.comment.entity.Comment;
import com.ll.medium.domain.post.comment.form.CommentForm;
import com.ll.medium.domain.post.comment.service.CommentService;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;
    private final MemberService memberService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write/{id}")
    public String write(Model model, @PathVariable("id") Integer id,
                        @Valid CommentForm commentForm, BindingResult bindingResult ,
                        Principal principal) {
        Post post = postService.getPost(id);
        Member member = memberService.getMember(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "domain/post/post/detail";
        }
        commentService.create(post, commentForm.getContent(), member);
        return String.format("redirect:/post/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String commentModify(CommentForm commentForm, @PathVariable("id") Integer id, Principal principal) {
        Comment comment = commentService.getComment(id);
        if (!comment.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        commentForm.setContent(comment.getContent());
        return "domain/post/comment/write";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid CommentForm commentForm, BindingResult bindingResult,
                               @PathVariable("id") Integer id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "domain/post/comment/write";
        }
        Comment comment = commentService.getComment(id);
        if (!comment.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        commentService.modify(comment, commentForm.getContent());
        return String.format("redirect:/post/%s", comment.getPost().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String commentDelete(Principal principal, @PathVariable("id") Integer id) {
        Comment comment = commentService.getComment(id);
        if (!comment.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.commentService.delete(comment);
        return String.format("redirect:/post/%s", comment.getPost().getId());
    }
}
