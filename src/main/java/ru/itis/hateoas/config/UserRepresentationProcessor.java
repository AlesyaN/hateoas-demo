package ru.itis.hateoas.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import ru.itis.hateoas.models.User;

public class UserRepresentationProcessor implements RepresentationModelProcessor<EntityModel<User>> {
    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        User user = model.getContent();



        return model;
    }
}
