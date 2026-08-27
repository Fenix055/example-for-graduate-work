package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.AdService;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final AdService adService;


    @Operation(summary = "Получение комментариев объявления", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable int id) {
        log.info("Request to get comments for ad id: {}", id);
        if (adService.getAdDetails(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Comments comments = commentService.getComments(id);
        return ResponseEntity.ok(comments);
    }





    @Operation(summary = "Добавление комментария к объявлению", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable int id,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                              Authentication authentication) {
        log.info("Request to add comment for ad id: {} by user: {}", id, authentication.getName());
        if (adService.getAdDetails(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Comment comment = commentService.addComment(id, createOrUpdateComment, authentication);
        return ResponseEntity.ok(comment);
    }





    @Operation(summary = "Удаление комментария", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId,
                                           @PathVariable int commentId) {
        log.info("Request to delete comment id: {} from ad id: {}", commentId, adId);
        if (adService.getAdDetails(adId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }






    @Operation(summary = "Обновление комментария", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int adId,
                                                 @PathVariable int commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        log.info("Request to update comment id: {} from ad id: {}", commentId, adId);
        if (adService.getAdDetails(adId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Comment comment = commentService.updateComment(adId, commentId, createOrUpdateComment);
        return ResponseEntity.ok(comment);
    }

}
