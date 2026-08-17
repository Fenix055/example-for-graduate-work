package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable int id) {
        log.info("Request to get comments for ad id: {}", id);
        Comments dummyComments = new Comments();
        return ResponseEntity.ok(dummyComments);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable int id,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        log.info("Request to add comment for ad id: {}", id);
        Comment dummyComment = new Comment();
        return ResponseEntity.ok(dummyComment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId,
                                           @PathVariable int commentId) {
        log.info("Request to delete comment id: {} from ad id: {}", commentId, adId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int adId,
                                                 @PathVariable int commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        log.info("Request to update comment id: {} from ad id: {}", commentId, adId);
        Comment dummyComment = new Comment();
        return ResponseEntity.ok(dummyComment);
    }

}
