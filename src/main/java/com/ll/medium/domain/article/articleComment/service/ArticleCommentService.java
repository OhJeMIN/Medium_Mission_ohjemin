package com.ll.medium.domain.article.articleComment.service;

import com.ll.medium.domain.article.article.entity.Article;
import com.ll.medium.domain.article.articleComment.entity.ArticleComment;
import com.ll.medium.domain.article.articleComment.repository.ArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;

    public void create(Article article , String content){
        ArticleComment articleComment = new ArticleComment();
        articleComment.setContent(content);
        articleComment.setCreateDate(LocalDateTime.now());
        articleComment.setArticle(article);
        articleCommentRepository.save(articleComment);
    }

}
