package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toDto(CommentModel model) {
        Comment dto = new Comment();

        if (model == null) {
            return dto;
        }

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
        CommentModel model = new CommentModel();

        if (dto == null) {
            return model;
        }

        model.setText(dto.getText());
        return model;
    }

}
