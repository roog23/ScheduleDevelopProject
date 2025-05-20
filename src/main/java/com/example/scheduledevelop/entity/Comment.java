package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Comment extends Date{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(User user, Schedule schedule, String comment) {
        this.user = user;
        this.schedule = schedule;
        this.comment = comment;
    }

    public Comment() {
    }

    public void update(String comment) {
        this.comment = comment;
    }
}
