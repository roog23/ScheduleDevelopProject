package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 유저 정보를 담는 엔티티입니다.
 */
@Getter
@Entity
public class User extends Date{

    //유저 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    //유저 이름
    @Column(nullable = false)
    private String username;

    //유저 이메일
    @Column(nullable = false, unique = true)
    private String email;

    //유저 비밀번호
    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void updateUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
