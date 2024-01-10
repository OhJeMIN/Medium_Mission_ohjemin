package com.ll.medium.domain.home.home.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class HomeController {

    @DisplayName("t1")
    @Test
    void t1(){

    }
    /*@Test
    void testJpa() {
        Member memberUser1 = memberService.joinPaid("user1", "1234" ,true);
        Member memberUser2 = memberService.joinPaid("user2", "1234", true);
        Member memberUser3 = memberService.joinPaid("user3", "1234",true);
        Member memberUser4 = memberService.joinPaid("user4", "1234",true);


        IntStream.rangeClosed(5, 50).forEach(i -> {
            memberService.joinPaid( "user" + i, "1234", true);
        });
        IntStream.rangeClosed(51, 100).forEach(i -> {
            memberService.joinPaid( "user" + i, "1234", false);
        });


        postService.write("제목 1", "내용 1", true ,memberUser1);
        postService.write( "제목 2", "내용 2", true, memberUser1);
        postService.write( "제목 3", "내용 3", false, memberUser1);
        postService.write( "제목 4", "내용 4", true, memberUser1);

        postService.write( "제목 5", "내용 5", true, memberUser2);
        postService.write( "제목 6", "내용 6", false, memberUser2);

        IntStream.rangeClosed(7, 100).forEach(i -> {
            postService.write( "제목 " + i, "내용 " + i, true, memberUser3 );
        });
    }*/
}
