package com.ll.medium.domain.post.comment.entity;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Comment extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Post post;
}