package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);
}
