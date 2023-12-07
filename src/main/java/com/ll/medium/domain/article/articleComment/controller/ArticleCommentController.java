package com.ll.medium.domain.article.articleComment.controller;

import com.ll.medium.domain.article.article.entity.Article;
import com.ll.medium.domain.article.article.service.ArticleService;
import com.ll.medium.domain.article.articleComment.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/articleComment")
@RequiredArgsConstructor
@Controller
public class ArticleCommentController {
    private final ArticleService articleService;
    private final ArticleCommentService articleCommentService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
        Article article = this.articleService.getArticle(id);
        articleCommentService.create(article, content);
        return String.format("redirect:/article/detail/%s", id);
    }
}
