package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
