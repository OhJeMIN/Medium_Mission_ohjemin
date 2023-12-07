package com.ll.medium.domain.post.comment.service;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.comment.entity.Comment;
import com.ll.medium.domain.post.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(Post post , String content){
        Comment Comment = new Comment();
        Comment.setContent(content);
        Comment.setCreateDate(LocalDateTime.now());
        Comment.setPost(post);
        commentRepository.save(Comment);
    }

}
