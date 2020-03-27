package ru.itis.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.hateoas.enums.Access;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String password;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Access access;

    @OneToMany(mappedBy = "receiver")
    @OrderBy("date DESC")
    private List<Question> questions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscriptions",
            joinColumns = @JoinColumn(name = "subscriptor_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    private List<User> followers;

    @ManyToMany(mappedBy = "followers")
    private List<User> followings;


    public List<Question> getAnsweredQuestions() {
        List<Question> questions = new ArrayList<>();
        for (Question question : this.questions) {
            if (question.getAnswer() != null)
                questions.add(question);
        }
        return questions;
    }

    public List<Question> getUnansweredQuestions() {
        List<Question> questions = new ArrayList<>();
        for (Question question : this.questions) {
            if (question.getAnswer() == null)
                questions.add(question);
        }
        return questions;
    }

}
