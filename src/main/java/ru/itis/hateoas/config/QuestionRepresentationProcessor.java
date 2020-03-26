package ru.itis.hateoas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controllers.QuestionController;
import ru.itis.hateoas.models.Question;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuestionRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Question>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Question> process(EntityModel<Question> model) {
        Question question = model.getContent();

        if (question.getAnswer() == null) {
            Map<String, String> param = new HashMap<>();
            param.put("answer", question.getAnswer());
            model.add(linkTo(methodOn(QuestionController.class).answer(question.getId(), param)).withRel("answer"));
        }
        return model;
    }
}
