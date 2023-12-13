package com.ll.medium;

import com.ll.medium.domain.post.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class MediumApplicationTests {

    @Autowired
    private PostService postService;

    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String title = String.format("테스트 데이터입니다:[%03d]", i);
            String body = "내용무";
            this.postService.write(title, body , null);
        }
    }
}
