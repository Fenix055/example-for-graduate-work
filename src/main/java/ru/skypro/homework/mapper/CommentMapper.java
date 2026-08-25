package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toDto(CommentModel model) {
        if (model == null) {
            return null;
        }

        Comment dto = new Comment();

        dto.setPk(model.getPk());
        dto.setText(model.getText());
        dto.setCreatedAt(model.getCreatedAt());

        if (model.getAuthor() != null) {
            dto.setAuthor(model.getAuthor().getId());
            dto.setAuthorFirstName(model.getAuthor().getFirstName());
            dto.setAuthorImage(model.getAuthor().getImage());
        }
        return dto;
    }

    public CommentModel toModel(CreateOrUpdateComment dto) {
        if (dto == null) {
            return null;
        }
        CommentModel model = new CommentModel();

        model.setText(dto.getText());
        return model;
    }

}
