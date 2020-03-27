package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/users/{user-id}/block")
    public @ResponseBody
    EntityModel<User> block(@PathVariable("user-id") Long userId) {
        return new EntityModel<>(userService.block(userId));
    }

    @PutMapping("/users/{user-id}/unblock")
    public @ResponseBody
    EntityModel<User> unblock(@PathVariable("user-id") Long userId) {
        return new EntityModel<>(userService.unblock(userId));
    }
}
