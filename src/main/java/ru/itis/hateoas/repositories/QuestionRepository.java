package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
