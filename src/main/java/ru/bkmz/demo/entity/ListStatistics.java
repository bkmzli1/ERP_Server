package ru.bkmz.demo.entity;

import com.fasterxml.jackson.annotation.JsonView;

public class ListStatistics {

    @JsonView(Views.IdName.class)
    private String name;
    @JsonView(Views.IdName.class)
    private float balls;

    public ListStatistics(String name, float balls) {
        this.name = name;
        this.balls = balls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalls() {
        return balls;
    }

    public void setBalls(float balls) {
        this.balls = balls;
    }
}
