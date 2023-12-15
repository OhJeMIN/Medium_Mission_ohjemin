package com.ll.medium.domain.member.member.role;

import lombok.Getter;

@Getter
public enum MemberRole { // 열거 자료형(enum) , 상수 자료형으로 @Getter만 사용
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    MemberRole(String value) {
        this.value = value;
    }

    private String value;
}
