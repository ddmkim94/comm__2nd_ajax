import com.fasterxml.jackson.databind.ObjectMapper;
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
    void ObjectMapperTest() throws Exception {
        ArticleDto articleDto = new ArticleDto(1, "제목", "내용");

        String json = Ut.json.toJsonStr(articleDto, "");
        assertThat(json).isNotBlank();
        assertThat(json).isEqualTo("""
                {"id":1,"title":"제목","body":"내용"}
                """.trim());
    }
}
