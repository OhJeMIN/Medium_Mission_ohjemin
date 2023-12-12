package com.ll.medium.domain.user.user.service;

import com.ll.medium.domain.user.user.entity.Member;
import com.ll.medium.domain.user.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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

}
