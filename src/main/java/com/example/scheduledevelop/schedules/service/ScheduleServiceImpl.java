package com.example.scheduledevelop.schedules.service;


import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.WrongPasswordException;
import com.example.scheduledevelop.schedules.dto.response.ScheduleInfoResponseDto;
import com.example.scheduledevelop.schedules.repository.ScheduleRepository;
import com.example.scheduledevelop.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    @Override
    public ScheduleInfoResponseDto create(Long id, String title, String text) {
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

    @Override
    public void delete(Long id, String password) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        if(!schedule.getUser().getPassword().equals(password)) {
            throw new WrongPasswordException();
        }
        scheduleRepository.delete(schedule);
    }

    @Transactional
    @Override
    public ScheduleInfoResponseDto scheduleUpdate(Long id, String password, String title, String text) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        if(!schedule.getUser().getPassword().equals(password)) {
            throw new WrongPasswordException();
        }

        schedule.updateSchedule(title, text);
        return new ScheduleInfoResponseDto(schedule.getId(), schedule.getUser().getUsername(), schedule.getTitle(), schedule.getText(), schedule.getCreateAt(), schedule.getUpdateAt());
    }
}
