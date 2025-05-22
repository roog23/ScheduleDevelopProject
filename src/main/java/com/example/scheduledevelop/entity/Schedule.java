package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 할일 정보를 담는 엔티티입니다.
 */
@Getter
@Entity
public class Schedule extends Date {

    //할일 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    //할일 제목
    @Column(nullable = false)
    private String title;

    //할일 내용
    @Column(nullable = false)
    private String text;

    //할일 작성자
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
