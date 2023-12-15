package com.ll.medium.domain.post.post.repository;

import com.ll.medium.domain.post.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAll(Pageable pageable);
    Page<Post> findByIsPublishedTrue(Pageable pageable);

    Page<Post> findByMemberId(Pageable pageable, Long id);
    Page<Post> findAllByOrderByCreateDateDesc(Pageable pageable);
}
