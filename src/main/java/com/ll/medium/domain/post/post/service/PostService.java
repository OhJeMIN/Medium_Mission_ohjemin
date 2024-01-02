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
        Optional<Post> postOptional = this.postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.increaseViewCount();
            postRepository.save(post); // 변경된 조회수를 데이터베이스에 저장
            return post;
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
        Pageable pageable = PageRequest.of(0, 30, Sort.by(sorts));
        return postRepository.findAll(pageable);
    }

    public Page<Post> getLatestPosts() {
        Pageable pageable = PageRequest.of(0, 30, Sort.by("createDate").descending());
        return postRepository.findAllByOrderByCreateDateDesc(pageable);
    }

    public Page<Post> getListByMemberId(int page, Long id){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 30, Sort.by(sorts));
        return postRepository.findByMemberId(pageable, id);
    }

    public Page<Post> getListIsPublished(List<String> kwTypes, String kw, int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 30, Sort.by(sorts));

        if (kwTypes.contains("title") && kwTypes.contains("body") && kwTypes.contains("")) {
            return postRepository.findByTitleContainingOrBodyContaining(kw, kw, pageable);
        } else if (kwTypes.contains("title")) {
            return postRepository.findByTitleContaining(kw, pageable);
        } else if (kwTypes.contains("body")) {
            return postRepository.findByBodyContaining(kw, pageable);
        }


        return postRepository.findByIsPublishedTrue(pageable);
    }

    public void modify(Post post, String title, String body , Boolean ispublished) {
        post.setTitle(title);
        post.setBody(body);
        post.setModifyDate(LocalDateTime.now());
        post.setPublished(ispublished);
        postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void like(Post post, Member member) {
        post.getLike().add(member);
        postRepository.save(post);
    }

    public void cacelLike(Post post, Member member) {
        post.removeLikeById(member.getId());
        postRepository.save(post);
    }
}

