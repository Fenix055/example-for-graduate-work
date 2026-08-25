package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    @Operation(summary = "Получение комментариев объявления", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable int id) {
        log.info("Request to get comments for ad id: {}", id);
        Comments dummyComments = new Comments();
        return ResponseEntity.ok(dummyComments);
    }

    @Operation(summary = "Добавление комментария к объявлению", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable int id,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        log.info("Request to add comment for ad id: {}", id);
        Comment dummyComment = new Comment();
        return ResponseEntity.ok(dummyComment);
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
        Comment dummyComment = new Comment();
        return ResponseEntity.ok(dummyComment);
    }

}
