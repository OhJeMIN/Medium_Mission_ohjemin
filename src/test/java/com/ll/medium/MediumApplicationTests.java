package com.ll.medium;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
@SpringBootTest
public class MediumApplicationTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    void testJpa() {
        Post q1 = new Post();
        q1.setTitle("제목 1");
        q1.setBody("내용 1");
        q1.setCreateDate(LocalDateTime.now());
        this.postRepository.save(q1);  // 첫번째 질문 저장

        Post q2 = new Post();
        q2.setTitle("제목 2");
        q2.setBody("내용 2");
        q2.setCreateDate(LocalDateTime.now());
        this.postRepository.save(q2);  // 두번째 질문 저장
    }
}
