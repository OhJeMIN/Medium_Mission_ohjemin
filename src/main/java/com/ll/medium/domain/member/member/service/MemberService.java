package com.ll.medium.domain.member.member.service;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.repository.MemberRepository;
import com.ll.medium.global.ut.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {
    final private MemberRepository memberRepository;
    final private PasswordEncoder passwordEncoder;

    public Member join(String username, String password) {
        Member user = new Member();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        memberRepository.save(user);
        return user;
    }
    public Member getMember(String username) {
        Optional<Member> member = this.memberRepository.findByusername(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }
}
