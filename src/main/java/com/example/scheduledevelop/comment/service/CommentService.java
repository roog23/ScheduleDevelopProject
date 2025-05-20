package com.example.scheduledevelop.comment.service;

import com.example.scheduledevelop.comment.dto.response.CommentInfoResponseDto;
import com.example.scheduledevelop.comment.dto.response.InfoAllResponseDto;

public interface CommentService {
    CommentInfoResponseDto commentCreate(Long userId, Long scheduleId, String comment);

    InfoAllResponseDto commentFindById(Long id);

    CommentInfoResponseDto commentUpdate(Long id, Long userId, String password, String comment);

    void commentDelete(Long id, Long userId, String password);
}
