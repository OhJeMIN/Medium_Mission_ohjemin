package com.ll.medium.domain.article.articleComment.repository;

import com.ll.medium.domain.article.articleComment.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {
}
