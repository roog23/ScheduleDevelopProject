package com.example.scheduledevelop.schedules.service;


import com.example.scheduledevelop.comment.repository.CommentRepository;
import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.entity.Comment;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 할일 정보를 관리하는 서비스 구현체입니다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;

    /**
     * 할일을 생성합니다.
     * @param userId    생성할 할일을 작성한 작성자의 식별자
     * @param title     생성할 할일의 제목
     * @param text      생성할 할일의 내용
     * @return          생성된 할일의 정보
     */
    @Override
    public ScheduleInfoResponseDto scheduleCreate(Long userId, String title, String text) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Schedule schedule = new Schedule(title, text);
        schedule.setUser(user);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleInfoResponseDto(savedSchedule.getScheduleId(), savedSchedule.getUser().getUsername(), savedSchedule.getTitle(), savedSchedule.getText(), savedSchedule.getCreateAt(), savedSchedule.getUpdateAt());
    }

    /**
     * 할일 식별자를 통한 할일을 조회합니다.
     * @param scheduleId    찾길 원하는 할일의 식별자
     * @return      찾은 할일의 정보
     */
    @Override
    @Transactional(readOnly = true)
    public ScheduleInfoResponseDto scheduleFindById(Long scheduleId) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        return new ScheduleInfoResponseDto(findSchedule.getScheduleId(), findSchedule.getUser().getUsername(), findSchedule.getTitle(), findSchedule.getText(), findSchedule.getCreateAt(), findSchedule.getUpdateAt());
    }

    /**
     * 할일 정보를 수정합니다.
     * @param scheduleId    수정을 원하는 할일의 식별자
     * @param userId        수정을 요청한 유저
     * @param password      입력받은 비밀번호
     * @param title         수정할 할일 제목
     * @param text          수정할 할일 내용
     * @return              수정된 할일의 정보
     */
    @Override
    public ScheduleInfoResponseDto scheduleUpdate(Long scheduleId, Long userId, String password, String title, String text) {
        Schedule schedule = checkUserIdAndPassword(scheduleId, userId, password);
        schedule.updateSchedule(title, text);

        return new ScheduleInfoResponseDto(schedule.getScheduleId(), schedule.getUser().getUsername(), schedule.getTitle(), schedule.getText(), schedule.getCreateAt(), schedule.getUpdateAt());
    }

    /**
     * 할일 정보를 삭제합니다.
     * @param scheduleId    삭제를 원하는 할일의 식별자
     * @param userId        삭제를 요청한 유저
     * @param password      입력받은 비밀번호
     */
    @Override
    public void scheduleDelete(Long scheduleId, Long userId, String password) {
        scheduleRepository.delete(checkUserIdAndPassword(scheduleId, userId, password));
    }

    /**
     * 할일을 페이지로 조회합니다.
     * @param pageNum   페이지 번호
     * @param pageSize  페이지 크기
     * @return          페이지에 해당하는 할일의 정보
     */
    @Override
    @Transactional(readOnly = true)
    public List<SchedulePageInfoResponseDto> schedulePage(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1 , pageSize, Sort.by(Sort.Direction.DESC, "updateAt"));
        Page<Schedule> pageList = scheduleRepository.findAllWithUser(pageable);
        //페이지에 포함되는 할일 식별자 목록을 만듭니다.
        List<Long> schedule = pageList.getContent().stream().map(Schedule::getScheduleId).toList();
        //페이지에 포함되는 할일의 댓글 목록을 만듭니다.
        List<Comment> scheduleComment = commentRepository.findBySchedule_ScheduleIdIn(schedule);
        Map<Long, Integer> commentCount = new HashMap<>();
        //할일에 작성된 댓글 수를 정리합니다.
        for(Comment comment : scheduleComment) {
            Long scheduleId = comment.getSchedule().getScheduleId();
            int count = commentCount.getOrDefault(scheduleId, 0);
            commentCount.put(scheduleId, count + 1);
        }

        return pageList.getContent().stream()
                .map(m ->
                        new SchedulePageInfoResponseDto(m.getScheduleId(),
                                m.getTitle(),
                                m.getText(),
                                commentCount.getOrDefault(m.getScheduleId(),0), //댓글 수를 가져오고, 값이 없으면 0을 출력합니다.
                                m.getCreateAt(),
                                m.getUpdateAt(),
                                m.getUser().getUsername()))
                .toList();
    }

    /**
     * 작성자와 요청한 유저가 동일한지, 입력받은 비밀번호가 일치하는지 확인합니다.
     * @param scheduleId    할일 식별자
     * @param userId        유저 식별자
     * @param password      입력받은 비밀번호
     * @return              할일의 정보
     */
    private Schedule checkUserIdAndPassword(Long scheduleId, Long userId, String password) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        //할일 작성자와 현재 유저가 같은지 확인합니다.
        if(!schedule.getUser().getUserId().equals(userId)) {
            throw new WrongUserException();
        }

        //입력받은 비밀번호와 저장된 비밀번호가 일치하는지 확인합니다.
        if(passwordEncoder.matches(password, schedule.getUser().getPassword())) {
            throw new WrongPasswordException();
        }
        return schedule;
    }
}