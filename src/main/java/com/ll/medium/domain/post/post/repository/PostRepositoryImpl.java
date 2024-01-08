package com.ll.medium.domain.post.post.repository;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.entity.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public PostRepositoryImpl() {
        super(Post.class);
    }

    @Override
    public Page<Post> search(List<String> kwTypes, String kw, String sort, Pageable pageable) {
        QPost post = QPost.post;

        BooleanBuilder builder = new BooleanBuilder();

        if(kwTypes.contains("authorUsername")) {
            builder.and(post.member.username.contains(kw));
        }

        if(kwTypes.contains("title")) {
            builder.and(post.title.contains(kw));
        }

        if(kwTypes.contains("body")) {
            builder.and(post.body.contains(kw));
        }

        OrderSpecifier<?> orderSpecifier;
        switch (sort) {
            case "desc":
                orderSpecifier = post.id.desc();
                break;
            case "asc":
                orderSpecifier = post.id.asc();
                break;
            case "postLikeDesc":
                orderSpecifier = Expressions.numberTemplate(Long.class, "size({0})", post.like).desc();
                break;
            case "postLikeAsc":
                orderSpecifier = Expressions.numberTemplate(Long.class, "size({0})", post.like).asc();
                break;
            case "viewCountDesc":
                orderSpecifier = post.viewCount.desc();
                break;
            case "viewCountAsc":
                orderSpecifier = post.viewCount.asc();
                break;
            default:
                throw new IllegalArgumentException("Sorting parameter is not valid");
        }

        List<Post> posts = jpaQueryFactory.selectFrom(post)
                .where(builder)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(post)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(posts, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), total);
    }
}
