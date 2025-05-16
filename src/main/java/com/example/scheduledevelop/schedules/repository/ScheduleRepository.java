package com.example.scheduledevelop.schedules.repository;

import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.exception.ScheduleNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(ScheduleNotFoundException::new);
    }
}
