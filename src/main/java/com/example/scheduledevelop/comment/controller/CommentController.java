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

/**
 * 댓글 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@AllArgsConstructor
@RequestMapping("comments")
public class CommentController {
    public final CommentService commentService;

    /**
     * 댓글을 생성합니다.
     * @param scheduleId    댓글을 작성하는 할일 식별자
     * @param requestDto    댓글 정보를 담고 있는 요청 DTO
     * @param request       세션 키 정보 (userId)를 얻기 위해 사용
     * @return              생성된 댓글 정보, HttpStatus.CREATED 응답
     */
    @PostMapping("/{scheduleId}")
    public ResponseEntity<CommentInfoResponseDto> commentCreate(@PathVariable Long scheduleId, @Valid @RequestBody CreateCommentRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        CommentInfoResponseDto response = commentService.commentCreate(userId, scheduleId, requestDto.getComment());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * 댓글 식별자를 통한 댓글 조회입니다.
     * @param commentId 조회하고자 하는 댓글 식별자
     * @return          조회된 댓글 정보, HttpStatus.OK 응답
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<InfoAllResponseDto> commentFindById(@PathVariable Long commentId) {
        InfoAllResponseDto response = commentService.commentFindById(commentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 댓글 정보를 수정합니다.
     * @param commentId     수정하고자 하는 댓글 식별자
     * @param requestDto    수정하고자 하는 댓글 내용을 담고 있는 요청 DTO
     * @param request       세션 키 정보 (userId)를 얻기 위해 사용
     * @return              수정된 댓글 정보, HttpStatus.OK 응답
     */
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentInfoResponseDto> commentUpdate(@PathVariable Long commentId, @Valid @RequestBody UpdateCommentRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        CommentInfoResponseDto response = commentService.commentUpdate(commentId, userId, requestDto.getPassword(), requestDto.getComment());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 댓글 정보를 삭제합니다.
     * @param commentId     삭제를 원하는 댓글 식별자
     * @param requestDto    삭제를 원하는 댓글 작성자의 비밀번호를 담고 있는 요청 DTO
     * @param request       세션 키 정보 (userId)를 얻기 위해 사용
     * @return              HttpStatus.OK 응답
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> commentDelete(@PathVariable Long commentId, @Valid @RequestBody DeleteCommentRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");
        commentService.commentDelete(commentId, userId, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
