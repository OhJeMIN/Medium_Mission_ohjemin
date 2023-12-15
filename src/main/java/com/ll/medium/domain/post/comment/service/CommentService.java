package com.ll.medium.domain.post.comment.service;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.post.comment.entity.Comment;
import com.ll.medium.domain.post.comment.repository.CommentRepository;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.global.ut.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(Post post , String content , Member member){
        Comment Comment = new Comment();
        Comment.setContent(content);
        Comment.setCreateDate(LocalDateTime.now());
        Comment.setPost(post);
        Comment.setMember(member);
        commentRepository.save(Comment);
    }

    public Comment getComment(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }

    public void modify(Comment comment, String content) {
        comment.setContent(content);
        comment.setModifyDate(LocalDateTime.now());
        this.commentRepository.save(comment);
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

}
