package ru.itis.hateoas.models.projections;

import org.springframework.data.rest.core.config.Projection;
import ru.itis.hateoas.models.Question;

@Projection(name="questionProjection", types = Question.class)
public interface QuestionProjection {
    String getText();
    String getAnswer();
}
