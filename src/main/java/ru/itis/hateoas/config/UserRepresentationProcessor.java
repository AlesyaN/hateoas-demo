package ru.itis.hateoas.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controllers.QuestionController;
import ru.itis.hateoas.controllers.UserController;
import ru.itis.hateoas.enums.Access;
import ru.itis.hateoas.models.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRepresentationProcessor implements RepresentationModelProcessor<EntityModel<User>> {
    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        User user = model.getContent();
        if (user.getAccess().equals(Access.ALLOWED)) {
            model.add(linkTo(methodOn(UserController.class).block(user.getId())).withRel("block"));
        } else {
            model.add(linkTo(methodOn(UserController.class).unblock(user.getId())).withRel("unblock"));
        }
        return model;
    }
}
