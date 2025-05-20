package com.example.scheduledevelop.schedules.service;


import com.example.scheduledevelop.comment.repository.CommentRepository;
import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.WrongPasswordException;
import com.example.scheduledevelop.exception.WrongUserException;
import com.example.scheduledevelop.schedules.dto.response.ScheduleInfoResponseDto;
import com.example.scheduledevelop.schedules.dto.response.SchedulePageInfoResponseDto;
import com.example.scheduledevelop.schedules.repository.ScheduleRepository;
import com.example.scheduledevelop.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;

    @Override
    public ScheduleInfoResponseDto scheduleCreate(Long id, String title, String text) {
        User user = userRepository.findByIdOrElseThrow(id);
        Schedule schedule = new Schedule(title, text);
        schedule.setUser(user);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleInfoResponseDto(savedSchedule.getId(), savedSchedule.getUser().getUsername(), savedSchedule.getTitle(), savedSchedule.getText(), savedSchedule.getCreateAt(), savedSchedule.getUpdateAt());
    }

    @Override
    public ScheduleInfoResponseDto scheduleFindById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleInfoResponseDto(findSchedule.getId(), findSchedule.getUser().getUsername(), findSchedule.getTitle(), findSchedule.getText(), findSchedule.getCreateAt(), findSchedule.getUpdateAt());
    }

    @Transactional
    @Override
    public ScheduleInfoResponseDto scheduleUpdate(Long id, Long userId, String password, String title, String text) {
        Schedule schedule = checkUserIdAndPassword(id, userId, password);
        schedule.updateSchedule(title, text);

        return new ScheduleInfoResponseDto(schedule.getId(), schedule.getUser().getUsername(), schedule.getTitle(), schedule.getText(), schedule.getCreateAt(), schedule.getUpdateAt());
    }

    @Override
    public void scheduleDelete(Long id, Long userId, String password) {
        scheduleRepository.delete(checkUserIdAndPassword(id, userId, password));
    }

    @Override
    public List<SchedulePageInfoResponseDto> schedulePage(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1 , pageSize, Sort.by(Sort.Direction.DESC, "updateAt"));
        Page<Schedule> pageList = scheduleRepository.findAll(pageable);
        return pageList.stream().map(m -> new SchedulePageInfoResponseDto(m.getTitle(), m.getText(), commentRepository.countByScheduleId(m.getId()), m.getCreateAt(), m.getUpdateAt(), m.getUser().getUsername())).toList();
    }

    private Schedule checkUserIdAndPassword(Long id, Long userId, String password) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        if(!schedule.getUser().getId().equals(userId)) {
            throw new WrongUserException();
        }

        if(passwordEncoder.matches(password, schedule.getUser().getPassword())) {
            throw new WrongPasswordException();
        }
        return schedule;
    }
}
