package com.example.scheduledevelop.comment.repository;

import com.example.scheduledevelop.entity.Comment;
import com.example.scheduledevelop.exception.CommentNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * 댓글 정보를 저장하는 JpaRepository 입니다.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 댓글 식별자를 통해 댓글을 조회하고, 존재하지 않으면 예외가 발생합니다.
     * @param commentId 조회하고자 하는 댓글 식별자
     * @return          조회된 댓글의 정보
     */
    default Comment findByIdOrElseThrow(Long commentId) {
        return findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    /**
     * 스케줄에 해당하는 댓글 목록을 반환합니다.
     * @param schedule  페이지에 해당하는 스케줄 목록
     * @return          스케줄 목록에 작성된 댓글 정보 목록
     */
    List<Comment> findBySchedule_ScheduleIdIn(Collection<Long> schedule);
}
