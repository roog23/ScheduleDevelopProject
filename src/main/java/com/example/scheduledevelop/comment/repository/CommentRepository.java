package com.example.scheduledevelop.comment.repository;

import com.example.scheduledevelop.entity.Comment;
import com.example.scheduledevelop.exception.CommentNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(CommentNotFoundException::new);
    }

    int countByScheduleId(Long id);
}
