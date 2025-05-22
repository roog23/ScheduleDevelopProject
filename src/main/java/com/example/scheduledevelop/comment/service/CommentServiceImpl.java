package com.example.scheduledevelop.comment.service;

import com.example.scheduledevelop.comment.dto.response.CommentInfoResponseDto;
import com.example.scheduledevelop.comment.dto.response.InfoAllResponseDto;
import com.example.scheduledevelop.comment.repository.CommentRepository;
import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.entity.Comment;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.WrongPasswordException;
import com.example.scheduledevelop.exception.WrongUserException;
import com.example.scheduledevelop.schedules.repository.ScheduleRepository;
import com.example.scheduledevelop.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 댓글 정보를 관리하는 서비스 구현체입니다.
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    public final CommentRepository commentRepository;
    public final UserRepository userRepository;
    public final ScheduleRepository scheduleRepository;
    public final PasswordEncoder passwordEncoder;

    /**
     * 댓글을 생성합니다.
     * @param userId        댓글 작성자
     * @param scheduleId    댓글을 작성할 할일
     * @param comment       생성할 댓글 정보
     * @return              생성된 댓글 정보
     */
    @Override
    public CommentInfoResponseDto commentCreate(Long userId, Long scheduleId, String comment) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        Comment createComment = new Comment(user, schedule, comment);
        Comment savedcomment = commentRepository.save(createComment);
        return new CommentInfoResponseDto(savedcomment.getCommentId(), savedcomment.getComment(), savedcomment.getUser().getUserId(), savedcomment.getSchedule().getScheduleId(), savedcomment.getCreateAt(), savedcomment.getUpdateAt());
    }

    /**
     * 댓글 식별자를 통한 댓글을 조회합니다.
     * @param commentId 찾길 원하는 댓글 식별자
     * @return          찾은 해당 댓글의 정보
     */
    @Override
    public InfoAllResponseDto commentFindById(Long commentId) {
        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);
        return new InfoAllResponseDto( findComment.getUser().getUsername(), findComment.getUser().getEmail(), findComment.getSchedule().getScheduleId(), findComment.getSchedule().getTitle(), findComment.getSchedule().getText(), findComment.getCommentId(), findComment.getComment(), findComment.getCreateAt(), findComment.getUpdateAt());
    }

    /**
     * 댓글 정보를 수정합니다.
     * @param commentId 수정을 원하는 댓글 식별자
     * @param userId    수정을 원하는 유저 식별자
     * @param password  입력받은 비밀번호
     * @param comment   수정할 댓글 내용
     * @return          수정된 댓글 정보
     */
    @Transactional
    @Override
    public CommentInfoResponseDto commentUpdate(Long commentId, Long userId, String password, String comment) {
        Comment findComment = checkUserAndPassword(commentId, userId, password);
        findComment.update(comment);
        return new CommentInfoResponseDto(findComment.getCommentId(), findComment.getComment(), findComment.getUser().getUserId(), findComment.getSchedule().getScheduleId(), findComment.getCreateAt(), findComment.getUpdateAt());
    }

    /**
     * 댓글 정보를 삭제합니다.
     * @param commentId 삭제를 원하는 댓글 식별자
     * @param userId    삭제를 원하는 유저 식별자
     * @param password  입력받은 비밀번호
     */
    @Transactional
    @Override
    public void commentDelete(Long commentId, Long userId, String password) {
        commentRepository.delete(checkUserAndPassword(commentId, userId, password));
    }

    /**
     * 작성자와 요청한 유저가 동일한지, 입력받은 비밀번호가 일치하는지 확인합니다.
     * @param commentId 댓글 식별자
     * @param userId    유저 식별자
     * @param password  입력받은 비밀번호
     * @return          댓글의 정보
     */
    private Comment checkUserAndPassword(Long commentId, Long userId, String password) {
        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);
        User user = userRepository.findByIdOrElseThrow(userId);

        //댓글 작성자와 현재 유저가 같은지 확인합니다.
        if(!findComment.getUser().getUserId().equals(userId)) {
            throw new WrongUserException();
        }

        //입력받은 비밀번호와 저장된 비밀번호가 일치하는지 확인합니다.
        if(passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException();
        }
        return findComment;
    }
}
