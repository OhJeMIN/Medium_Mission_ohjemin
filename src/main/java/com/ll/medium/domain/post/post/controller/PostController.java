package com.ll.medium.domain.post.post.controller;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.domain.post.comment.form.CommentForm;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.form.PostForm;
import com.ll.medium.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/list") // 공개된 전체 글 리스트
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0")int page){
        Page<Post> paging = postService.getListIsPublished(page);
        model.addAttribute("paging", paging);
        return "domain/post/post/list";
    }

    @GetMapping(value = "/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, CommentForm commentForm) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "domain/post/post/detail";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String getWrite(PostForm postForm) {
        return "domain/post/post/write";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public String postWrite(@Valid PostForm postForm, BindingResult bindingResult , Principal principal) {
        if(bindingResult.hasErrors()){
            return "domain/post/post/write";
        }
        Member member = memberService.getMember(principal.getName());
        postService.write(postForm.getTitle(),postForm.getBody(), postForm.isPublished(),member);
        return "redirect:/post/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping ("/modify/{id}")
    public String postModify(PostForm postForm , @PathVariable("id")Integer id, Principal principal){
        Post post = postService.getPost(id);
        if(!post.getMember().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        postForm.setTitle(post.getTitle());
        postForm.setBody(post.getBody());
        return "domain/post/post/write";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String postModify(@Valid PostForm postForm, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "domain/post/post/write";
        }
        Post post = postService.getPost(id);
        if (!post.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        postService.modify(post, postForm.getTitle(), postForm.getBody());
        return String.format("redirect:/post/%s", id);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String postDelete(Principal principal, @PathVariable("id") Integer id){
        Post post = postService.getPost(id);
        if (!post.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        postService.delete(post);
        return "redirect:/";
    }
}
