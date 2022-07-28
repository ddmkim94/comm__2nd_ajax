package com.ll.exam.article.dto;

import lombok.*;

@ToString
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private long id;
    private String title;
    private String body;

}
