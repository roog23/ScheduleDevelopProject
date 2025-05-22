package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 댓글 정보를 담는 엔티티입니다.
 */
@Getter
@Entity
public class Comment extends Date{

    //댓글 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    //댓글 내용
    @Column(nullable = false)
    private String comment;

    //댓글 작성자
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    //댓글을 적성한 할일
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
