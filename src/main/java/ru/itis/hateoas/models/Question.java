package ru.itis.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String text;
    private Date date;
    private String answer;
    private boolean anonymous;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @OrderBy("date")
    private List<Comment> comments;

}
