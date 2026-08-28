package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Реализация сервиса для работы с комментариями к объявлениям.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comments getComments(int id) {
        log.info("Business logic: getting comments for ad id {}", id);
        List<CommentModel> allComments = commentRepository.findAll();

        List<CommentModel> adComments = allComments.stream()
                .filter(comment -> comment.getAd() != null && comment.getAd().getPk() == id)
                .collect(Collectors.toList());

        Comments commentsDto = new Comments();
        commentsDto.setResults(adComments.stream().map(commentMapper::toDto).collect(Collectors.toList()));
        commentsDto.setCount(adComments.size());
        return commentsDto;
    }

    @Override
    public Comment addComment(int id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        log.info("Business logic: adding comment to ad id {} by user {}", id, authentication.getName());
        AdModel ad = adRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "Ad not found"));
        UserModel author = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        CommentModel commentModel = commentMapper.toModel(createOrUpdateComment);
        commentModel.setAd(ad);
        commentModel.setAuthor(author);
        commentModel.setCreatedAt(System.currentTimeMillis());

        CommentModel savedComment = commentRepository.save(commentModel);
        return commentMapper.toDto(savedComment);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or " +
            "@commentRepository.findById(#commentId).orElse(null) != null and " +
            "@commentRepository.findById(#commentId).get().author.email == authentication.name")
    public void deleteComment(int adId, int commentId) {
        log.info("Business logic: deleting comment id {} from ad id {}", commentId, adId);
        if (!commentRepository.existsById(commentId)) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or " +
            "@commentRepository.findById(#commentId).orElse(null) != null and " +
            "@commentRepository.findById(#commentId).get().author.email == authentication.name")
    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment) {
        log.info("Business logic: updating comment id {} from ad id {}", commentId, adId);
        CommentModel commentModel = commentRepository.findById(commentId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        commentModel.setText(createOrUpdateComment.getText());
        commentRepository.save(commentModel);
        return commentMapper.toDto(commentModel);
    }

}
