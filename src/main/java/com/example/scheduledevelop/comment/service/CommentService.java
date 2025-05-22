package com.example.scheduledevelop.comment.service;

import com.example.scheduledevelop.comment.dto.response.CommentInfoResponseDto;
import com.example.scheduledevelop.comment.dto.response.InfoAllResponseDto;

/**
 * 댓글 정보를 관리하는 서비스 인터페이스입니다.
 */
public interface CommentService {

    /**
     * 댓글을 생성합니다.
     * @param userId        댓글 작성자
     * @param scheduleId    댓글을 작성할 할일
     * @param comment       생성할 댓글 정보
     * @return              생성된 댓글 정보
     */
    CommentInfoResponseDto commentCreate(Long userId, Long scheduleId, String comment);

    /**
     * 댓글 식별자를 통한 댓글을 조회합니다.
     * @param commentId 찾길 원하는 댓글 식별자
     * @return          찾은 해당 댓글의 정보
     */
    InfoAllResponseDto commentFindById(Long commentId);

    /**
     * 댓글 정보를 수정합니다.
     * @param commentId 수정을 원하는 댓글 식별자
     * @param userId    수정을 원하는 유저 식별자
     * @param password  입력받은 비밀번호
     * @param comment   수정할 댓글 내용
     * @return          수정된 댓글 정보
     */
    CommentInfoResponseDto commentUpdate(Long commentId, Long userId, String password, String comment);

    /**
     * 댓글 정보를 삭제합니다.
     * @param commentId 삭제를 원하는 댓글 식별자
     * @param userId    삭제를 원하는 유저 식별자
     * @param password  입력받은 비밀번호
     */
    void commentDelete(Long commentId, Long userId, String password);
}
