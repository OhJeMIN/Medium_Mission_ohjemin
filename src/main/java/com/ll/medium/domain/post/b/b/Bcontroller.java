package com.ll.medium.domain.post.b.b;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.domain.post.comment.form.CommentForm;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/b")
public class Bcontroller {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/{username}")
    public String showMemberList(Model model,
                                 @RequestParam(value = "page", defaultValue = "0")int page,
                                 @PathVariable("username")String username){
        Member member = memberService.getMember(username);
        Page<Post> paging = postService.getListByMemberId(page,member.getId());
        model.addAttribute("paging", paging);
        return "domain/post/b/list";
    }

    @GetMapping("/{username}/{id}")
    public String showMemberDetail(Model model, @PathVariable("username") String username, @PathVariable("id") Integer id , CommentForm commentForm){
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "domain/post/b/detail";
    }
}
