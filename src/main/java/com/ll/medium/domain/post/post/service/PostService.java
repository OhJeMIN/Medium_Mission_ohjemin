package com.ll.medium.domain.post.post.service;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.repository.PostRepository;
import com.ll.medium.global.ut.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getList() {
        return this.postRepository.findAll();
    }

    public Post getPost(Integer id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new DataNotFoundException("Post not found");
        }
    }

    public void write(String title, String body , Member member) {
        Post post= new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setCreateDate(LocalDateTime.now());
        post.setMember(member);
        this.postRepository.save(post);
    }

    public Page<Post> getList(int page){
        Pageable pageable = PageRequest.of(page, 30);
        return this.postRepository.findAll(pageable);
    }
}

