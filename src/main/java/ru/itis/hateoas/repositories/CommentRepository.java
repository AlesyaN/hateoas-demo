package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
