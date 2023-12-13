package com.ll.medium.domain.post.post.service;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.repository.PostRepository;
import com.ll.medium.global.ut.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public void write(String title, String body, boolean isPublished , Member member) {
        Post post= new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setCreateDate(LocalDateTime.now());
        post.setMember(member);
        post.setPublished(isPublished);
        this.postRepository.save(post);
    }

    public Page<Post> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 30, Sort.by(sorts));
        return postRepository.findAll(pageable);
    }

    public Page<Post> getListById(int page, Long id){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 30, Sort.by(sorts));
        return postRepository.findById(pageable, id);
    }

    public Page<Post> getListIsPublished(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 30, Sort.by(sorts));
        return postRepository.findByIsPublishedTrue(pageable);
    }

    public void modify(Post post, String title, String body) {
        post.setTitle(title);
        post.setBody(body);
        post.setModifyDate(LocalDateTime.now());
        postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
}

