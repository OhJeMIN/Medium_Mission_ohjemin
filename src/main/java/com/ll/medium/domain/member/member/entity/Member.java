package com.ll.medium.domain.member.member.entity;

import com.ll.medium.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;

    private String password;

    @Column(name = "PAID", nullable = false, columnDefinition = "boolean default false")
    private boolean paid;
}
