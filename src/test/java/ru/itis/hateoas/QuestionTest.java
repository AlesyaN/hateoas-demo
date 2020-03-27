package ru.itis.hateoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.models.Question;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.services.QuestionService;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class QuestionTest {

    private final static String TEST_ANSWER = "V pravde.";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        when(questionService.answer(1L, TEST_ANSWER)).thenReturn(answeredQuestion());
    }

    @Test
    public void coursePublishTest() throws Exception {
        mockMvc.perform(put("/questions/1/answer").contentType(MediaType.APPLICATION_JSON).content("{\"answer\":\"" + TEST_ANSWER + "\"}")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value(answeredQuestion().getText()))
                .andExpect(jsonPath("$.answer").value(answeredQuestion().getAnswer()))
                .andExpect(jsonPath("$.anonymous").value(answeredQuestion().isAnonymous()))
                .andDo(document("answer_question", responseFields(
                        fieldWithPath("text").description("Текст вопроса"),
                        fieldWithPath("answer").description("Ответ на вопрос"),
                        fieldWithPath("date").description("Дата и время отправки вопроса"),
                        fieldWithPath("anonymous").description("Анонимный(true) или публичный(false) вопрос")
                )));
    }

    public Question answeredQuestion() {
        return Question.builder()
                .text("V chem sila?")
                .answer(TEST_ANSWER)
                .anonymous(true)
                .date(new Date())
                .sender(User.builder().login("lesya").build())
                .receiver(User.builder().login("stepa").build())
                .build();
    }

}
