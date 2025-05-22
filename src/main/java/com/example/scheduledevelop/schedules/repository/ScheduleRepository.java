package com.example.scheduledevelop.schedules.repository;

import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.exception.ScheduleNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 할일 정보를 저장하는 JpaRepository 입니다.
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    /**
     * 할일 식별자를 통해 할일을 조회하고, 존재하지 않으면 예외가 발생합니다.
     * @param scheduleId    조회하고자 하는 할일 식별자
     * @return              조회된 할일의 정보
     */
    default Schedule findByIdOrElseThrow(Long scheduleId){
        return findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
    }

    /**
     * 페이지에 해당하는 할일, 유저 정보를 조회합니다.
     * @param pageable  페이지에 대한 정보
     * @return          조회된 해당 페이지의 할일, 유저 정보
     */
    @Query("SELECT s FROM Schedule s JOIN FETCH s.user")
    Page<Schedule> findAllWithUser(Pageable pageable);
}
