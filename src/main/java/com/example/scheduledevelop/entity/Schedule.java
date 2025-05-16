package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Schedule extends Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Schedule(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Schedule() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateSchedule(String title, String text) {
        this.title =title;
        this.text = text;
    }
}
