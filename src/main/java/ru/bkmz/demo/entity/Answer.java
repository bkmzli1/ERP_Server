package ru.bkmz.demo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer {
    @JsonView(Views.Id.class)
    private String id;
    @JsonView(Views.IdName.class)
    private String answer;
    @JsonView(Views.FullMessage.class)
    private boolean YN;

    public Answer() {
    }

    public Answer(String answer, boolean YN) {
        this.answer = answer;
        this.YN = YN;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isYN() {
        return YN;
    }

    public void setYN(boolean YN) {
        this.YN = YN;
    }
}
