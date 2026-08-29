package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

/**
 * Сервис для управления комментариями к объявлениям.
 */

public interface CommentService {

    /**
     * Получает список всех комментариев, привязанных к конкретному объявлению.
     */
    Comments getComments(int id);

    /**
     * Добавляет новый комментарий к объявлению.
     */
    Comment addComment(int id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication);

    /**
     * Удаляет комментарий по его идентификатору.
     */
    void deleteComment(int adId, int commentId);


    /**
     * Обновляет текст существующего комментария.
     */
    Comment updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment);

}
