package com.ll.exam.article;

import com.ll.exam.ResultData;
import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArticleController {

    private final ArticleService articleService;

    public ArticleController() {
        articleService = new ArticleService();
    }

    public void showListAuto(Rq rq) {
        rq.view("usr/article/listAuto");
    }

    public void showList(Rq rq) {
        rq.setAttr("articles", articleService.findAll());
        rq.view("usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void write(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        long id = articleService.write(title, body);
        rq.replace("/usr/article/detail/free/%d".formatted(id), "%d번 게시물이 생성 되었습니다.".formatted(id));
    }

    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        if(id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }
        ArticleDto articleDto = articleService.findById(id);
        if(articleDto == null) {
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }

    public void deleteArticle(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        if(id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        articleService.delete(id);
        rq.replace("/usr/article/list/free", "%d번 게시물이 삭제되었습니다.".formatted(id));
    }

    public void showModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/modify");
    }

    public void modify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        articleService.modify(id, title, body);

        rq.replace("/usr/article/detail/free/%d".formatted(id), "%d번 게시물이 수정되었습니다.".formatted(id));
    }

    public void getArticles(Rq rq) {
        long fromId = rq.getLongParam("fromId", -1);

        List<ArticleDto> articleDtoList = null;

        if ( fromId == -1 ) {
            articleDtoList = articleService.findAll();
        }
        else {
            articleDtoList = articleService.findIdGreaterThan(fromId);
        }

        rq.successJson(articleDtoList);
    }
}