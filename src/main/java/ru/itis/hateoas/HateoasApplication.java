package ru.itis.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.hateoas.models.Message;
import ru.itis.hateoas.models.Question;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.repositories.*;

import java.util.Date;

import static java.util.Arrays.asList;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasApplication.class, args);

        CommentRepository commentRepository = context.getBean(CommentRepository.class);
        LikeRepository likeRepository = context.getBean(LikeRepository.class);
        MessageRepository messageRepository = context.getBean(MessageRepository.class);
        QuestionRepository questionRepository = context.getBean(QuestionRepository.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

        User lesya = User.builder()
                .login("lesya")
                .password("qwe")
                .build();

        User stepa = User.builder()
                .login("stepa")
                .password("qwe")
                .build();

        userRepository.saveAll(asList(lesya, stepa));

        Message message0 = Message.builder()
                .sender(lesya)
                .receiver(stepa)
                .text("hello")
                .date(new Date())
                .build();

        Message message1 = Message.builder()
                .sender(stepa)
                .receiver(lesya)
                .text("hi")
                .date(new Date())
                .build();

        messageRepository.saveAll(asList(message0, message1));

        Question question0 = Question.builder()
                .sender(stepa)
                .receiver(lesya)
                .text("V chem sila?")
                .anonymous(true)
                .date(new Date())
                .build();

        Question question1 = Question.builder()
                .sender(lesya)
                .receiver(stepa)
                .text("Skolko ty zarabatyvaesh?")
                .anonymous(true)
                .date(new Date())
                .build();

        Question question2 = Question.builder()
                .sender(lesya)
                .receiver(stepa)
                .text("Putin krasavchik?")
                .anonymous(false)
                .date(new Date())
                .build();

        questionRepository.saveAll(asList(question0, question1, question2));

    }

}
