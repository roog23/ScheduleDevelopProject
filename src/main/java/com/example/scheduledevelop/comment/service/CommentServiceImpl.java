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

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    public final CommentRepository commentRepository;
    public final UserRepository userRepository;
    public final ScheduleRepository scheduleRepository;
    public final PasswordEncoder passwordEncoder;

    @Override
    public CommentInfoResponseDto commentCreate(Long userId, Long scheduleId, String comment) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        Comment createComment = new Comment(user, schedule, comment);
        Comment savedcomment = commentRepository.save(createComment);
        return new CommentInfoResponseDto(savedcomment.getId(), savedcomment.getComment(), savedcomment.getUser().getId(), savedcomment.getSchedule().getId(), savedcomment.getCreateAt(), savedcomment.getUpdateAt());
    }

    @Override
    public InfoAllResponseDto commentFindById(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        return new InfoAllResponseDto( findComment.getUser().getUsername(), findComment.getUser().getEmail(), findComment.getSchedule().getId(), findComment.getSchedule().getTitle(), findComment.getSchedule().getText(), findComment.getId(), findComment.getComment(), findComment.getCreateAt(), findComment.getUpdateAt());
    }

    @Transactional
    @Override
    public CommentInfoResponseDto commentUpdate(Long id, Long userId, String password, String comment) {
        Comment findComment = checkUserAndPassword(id, userId, password);
        findComment.update(comment);
        return new CommentInfoResponseDto(findComment.getId(), findComment.getComment(), findComment.getUser().getId(), findComment.getSchedule().getId(), findComment.getCreateAt(), findComment.getUpdateAt());
    }

    @Override
    public void commentDelete(Long id, Long userId, String password) {
        commentRepository.delete(checkUserAndPassword(id, userId, password));
    }

    private Comment checkUserAndPassword(Long id, Long userId, String password) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        User user = userRepository.findByIdOrElseThrow(userId);
        if(!findComment.getUser().getId().equals(userId)) {
            throw new WrongUserException();
        }
        if(passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException();
        }
        return findComment;
    }
}
