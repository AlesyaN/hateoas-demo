package ru.itis.hateoas.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @NotEmpty
    private String text;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
