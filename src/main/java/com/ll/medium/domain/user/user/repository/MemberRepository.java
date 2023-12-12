package com.ll.medium.domain.user.user.repository;

import com.ll.medium.domain.user.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
