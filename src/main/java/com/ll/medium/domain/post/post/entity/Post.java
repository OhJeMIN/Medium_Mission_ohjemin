package com.ll.medium.domain.post.post.entity;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.post.comment.entity.Comment;
import com.ll.medium.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Post extends BaseEntity {

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = true)
    private boolean isPublished;

    @ManyToMany
    Set<Member> like;

    @Column(nullable = false)
    private int viewCount = 0;

    public void removeLikeById(Long memberId) {
        like.removeIf(member -> member.getId().equals(memberId));
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
}
