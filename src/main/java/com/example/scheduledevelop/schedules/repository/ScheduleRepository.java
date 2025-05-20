package com.example.scheduledevelop.schedules.repository;

import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.exception.ScheduleNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(ScheduleNotFoundException::new);
    }

    @Query("SELECT s FROM Schedule s JOIN FETCH s.user")
    Page<Schedule> findAllWithUser(Pageable pageable);
}
