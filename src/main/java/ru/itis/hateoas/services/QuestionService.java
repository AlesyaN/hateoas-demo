package ru.itis.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.models.Question;
import ru.itis.hateoas.repositories.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public Question answer(Long id, String answer) {
        Question question = questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        question.setAnswer(answer);
        questionRepository.save(question);
        return question;
    }
}
