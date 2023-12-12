package com.ll.medium.domain.user.user.entity;

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

}
