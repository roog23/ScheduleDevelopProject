package com.example.scheduledevelop.schedules.service;


import com.example.scheduledevelop.schedules.dto.response.ScheduleInfoResponseDto;
import com.example.scheduledevelop.schedules.dto.response.SchedulePageInfoResponseDto;

import java.util.List;

/**
 * 할일 정보를 관리하는 서비스 인터페이스입니다.
 */
public interface ScheduleService {

    /**
     * 할일을 생성합니다.
     * @param userId    생성할 할일을 작성한 작성자의 식별자
     * @param title     생성할 할일의 제목
     * @param text      생성할 할일의 내용
     * @return          생성된 할일의 정보
     */
    ScheduleInfoResponseDto scheduleCreate(Long userId, String title, String text);

    /**
     * 할일 식별자를 통한 할일을 찾습니다.
     * @param scheduleId    찾길 원하는 할일의 식별자
     * @return      찾은 할일의 정보
     */
    ScheduleInfoResponseDto scheduleFindById(Long scheduleId);

    /**
     * 할일 정보를 수정합니다.
     * @param scheduleId    수정을 원하는 할일의 식별자
     * @param userId        수정을 요청한 유저
     * @param password      입력받은 비밀번호
     * @param title         수정할 할일 제목
     * @param text          수정할 할일 내용
     * @return              수정된 할일의 정보
     */
    ScheduleInfoResponseDto scheduleUpdate(Long scheduleId, Long userId, String password,String title, String text);

    /**
     * 할일 정보를 삭제합니다.
     * @param scheduleId    삭제를 원하는 할일의 식별자
     * @param userId        삭제를 요청한 유저
     * @param password      입력받은 비밀번호
     */
    void scheduleDelete(Long scheduleId, Long userId, String password);

    /**
     * 할일을 페이지로 조회합니다.
     * @param pageNum   페이지 번호
     * @param pageSize  페이지 크기
     * @return          페이지에 해당하는 할일의 정보
     */
    List<SchedulePageInfoResponseDto> schedulePage(Integer pageNum, Integer pageSize);
}
