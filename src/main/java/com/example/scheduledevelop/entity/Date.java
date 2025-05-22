package com.example.scheduledevelop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

/**
 * 생성일과 수정일을 자동으로 관리하는 엔티티입니다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Date {

    //생성일
    @CreatedDate
    @Column(updatable = false)
    public LocalDate createAt;

    //마지막 수정일
    @LastModifiedDate
    public LocalDate updateAt;
}
