package com.example.scheduledevelop.comment.controller;

import com.example.scheduledevelop.comment.dto.request.CreateCommentRequestDto;
import com.example.scheduledevelop.comment.dto.request.DeleteCommentRequestDto;
import com.example.scheduledevelop.comment.dto.request.UpdateCommentRequestDto;
import com.example.scheduledevelop.comment.dto.response.CommentInfoResponseDto;
import com.example.scheduledevelop.comment.dto.response.InfoAllResponseDto;
import com.example.scheduledevelop.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("comments")
public class CommentController {
    public final CommentService commentService;

    @PostMapping("/{scheduleId}")
    public ResponseEntity<CommentInfoResponseDto> commentCreate(@PathVariable Long scheduleId, @Valid @RequestBody CreateCommentRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        CommentInfoResponseDto response = commentService.commentCreate(userId, scheduleId, requestDto.getComment());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoAllResponseDto> commentFindById(@PathVariable Long id) {
        InfoAllResponseDto response = commentService.commentFindById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentInfoResponseDto> commentUpdate(@PathVariable Long id, @Valid @RequestBody UpdateCommentRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        CommentInfoResponseDto response = commentService.commentUpdate(id, userId, requestDto.getPassword(), requestDto.getComment());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> commentDelete(@PathVariable Long id, @Valid @RequestBody DeleteCommentRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        commentService.commentDelete(id, userId, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
