package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.models.Question;
import ru.itis.hateoas.services.QuestionService;

import java.util.Map;

@RepositoryRestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PutMapping("/questions/{question-id}/answer")
    public @ResponseBody
    EntityModel<Question> answer(@PathVariable("question-id") Long questionId, @RequestBody Map<String, String> answer) {
        return new EntityModel<>(questionService.answer(questionId, answer.get("answer")));
    }



}
