package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
