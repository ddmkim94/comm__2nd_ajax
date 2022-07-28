import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    void assertThatTest() {
        int rs = 10 + 20;
        assertThat(rs).isEqualTo(30);
    }

    @Test
    void ObjectMapperTest__objectToJson() throws Exception {
        ArticleDto articleDto = new ArticleDto(1, "제목", "내용");
        String json = Ut.json.toStr(articleDto, "");

        assertThat(json).isNotBlank();
        assertThat(json).isEqualTo("""
                {"id":1,"title":"제목","body":"내용"}
                """.trim());
    }

    @Test
    void ObjectMapperTest__jsonToObject() throws JsonProcessingException {
        ArticleDto articleDtoOrigin = new ArticleDto(1, "제목", "내용");
        String json = Ut.json.toStr(articleDtoOrigin, "");

        ArticleDto articleDtoFromJson = Ut.json.toObj(json, ArticleDto.class, null);
        assertThat(articleDtoOrigin).isEqualTo(articleDtoFromJson);
    }

    @Test
    void ObjectMapperTest__listToJson() throws JsonProcessingException {
        List<ArticleDto> list = new ArrayList<>();
        list.add(new ArticleDto(1, "제목1", "내용1"));
        list.add(new ArticleDto(2, "제목2", "내용2"));
        list.add(new ArticleDto(3, "제목3", "내용3"));

        String json = Ut.json.toList(list, "");
        assertThat(json).isEqualTo("""
                [{"id":1,"title":"제목1","body":"내용1"},{"id":2,"title":"제목2","body":"내용2"},{"id":3,"title":"제목3","body":"내용3"}]
                """.trim());
        // 자바의 List를 JSON으로 변환하면 JS의 배열과 형식이 같다.
    }

    @Test
    void ObjectMapperTest__mapToJson() throws JsonProcessingException {
        Map<Integer, ArticleDto> map = new HashMap<>();
        map.put(1, new ArticleDto(1, "제목1", "내용1"));
        map.put(2, new ArticleDto(2, "제목2", "내용2"));
        map.put(3, new ArticleDto(3, "제목3", "내용3"));

        String json = Ut.json.toMap(map, "");
        assertThat(json).isEqualTo("""
                {"1":{"id":1,"title":"제목1","body":"내용1"},"2":{"id":2,"title":"제목2","body":"내용2"},"3":{"id":3,"title":"제목3","body":"내용3"}}
                """.trim());
        // 자바의 MAP을 JSON으로 변환하면 JS의 객체 형식과 같다.
    }

    @Test
    void ObjectMapperTest__jsonToList() throws JsonProcessingException {
        List<ArticleDto> list = new ArrayList<>();
        list.add(new ArticleDto(1, "제목1", "내용1"));
        list.add(new ArticleDto(2, "제목2", "내용2"));
        list.add(new ArticleDto(3, "제목3", "내용3"));

        String json = Ut.json.toList(list, "");

        List<ArticleDto> articleDtosFromJson = Ut.json.toObj(json, new TypeReference<List<ArticleDto>>() {
        }, null);

        assertThat(articleDtosFromJson).isEqualTo(list);
    }
}
