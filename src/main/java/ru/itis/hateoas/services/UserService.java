package ru.itis.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.enums.Access;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User block(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        user.setAccess(Access.BLOCKED);
        return userRepository.save(user);
    }

    public User unblock(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        user.setAccess(Access.ALLOWED);
        return userRepository.save(user);
    }
}
