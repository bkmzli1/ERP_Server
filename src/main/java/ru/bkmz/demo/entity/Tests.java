package ru.bkmz.demo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tests")
public class Tests {
    private String id;
    @JsonView(Views.IdName.class)
    private float balls;
    @JsonView(Views.IdName.class)
    private User user;

    public Tests() {
    }

    public Tests( float balls, User user) {
        this.balls = balls;
        this.user = user;
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

    public float getBalls() {
        return balls;
    }

    public void setBalls(float balls) {
        this.balls = balls;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "test_user",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
