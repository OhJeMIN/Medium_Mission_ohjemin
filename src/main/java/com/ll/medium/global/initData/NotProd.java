package com.ll.medium.global.initData;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class NotProd {

    private final MemberService memberService;
    private final PostService postService;

    @Bean
    public ApplicationRunner initNotProd(){
        return args -> {
            this.work1();
        };
    }

    private void work1() {
        if (memberService.findByUsername("user1").isPresent()) return;

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
    }


}
