import com.fasterxml.jackson.core.JsonProcessingException;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.Test;

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
}
