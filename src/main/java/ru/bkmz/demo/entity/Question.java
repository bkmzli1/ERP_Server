package ru.bkmz.demo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "questions")
public class Question {
    @JsonView(Views.Id.class)
    private String id;
    @JsonView(Views.IdName.class)
    private String name;
    @JsonView(Views.IdName.class)
    private String question;
    @JsonView(Views.IdName.class)
    private Set<Answer> answer;

    public Question() {
    }

    public Question(String name, String question, Set<Answer> answer) {
        this.name = name;
        this.question = question;
        this.answer = answer;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String questions) {
        this.question = questions;
    }
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "many_answer",
            joinColumns = @JoinColumn(name = "questions_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id", referencedColumnName = "id"))
    public Set<Answer> getAnswer() {
        Set<Answer> answers = new TreeSet<>(Comparator.comparing(Answer::getAnswer));
        answers.addAll(answer);
        return answers;
    }

    public void setAnswer(Set<Answer> answer) {
        this.answer = answer;
    }
}
