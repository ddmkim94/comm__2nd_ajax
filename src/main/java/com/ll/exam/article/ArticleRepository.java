package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleRepository {

    private static final List<ArticleDto> datum = new ArrayList<>();
    private static long lastId = 0;

    static {
        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "제목%d".formatted(id);
            String body = "내용%d".formatted(id);
            write(title, body);
        });
    }

    public static long write(String title, String body) {
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id, title, body);

        datum.add(newArticleDto);
        return id;
    }

    // 전체 글 목록 리턴
    public static List<ArticleDto> findAll() { return datum; }

    public static ArticleDto findById(long id) {
        for (ArticleDto articleDto : datum) {
            if(articleDto.getId() == id){
                return articleDto;
            }
        }
        return null;
    }

    public static void delete(long id) {
        ArticleDto articleDto = findById(id);
        if(articleDto == null) {
            return;
        }
        datum.remove(articleDto);
    }

    public static void modify(long id, String title, String body) {
        ArticleDto articleDto = findById(id);
        if(articleDto == null) {
            return;
        }

        articleDto.setTitle(title);
        articleDto.setBody(body);
    }

    public List<ArticleDto> findAllIdGreaterThan(long fromId) {
        return datum
                .stream()
                .filter(articleDto -> articleDto.getId() >= fromId)
                .collect(Collectors.toList());
    }
}
