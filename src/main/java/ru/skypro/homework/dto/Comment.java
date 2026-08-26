package ru.skypro.homework.dto;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Информация о комментарии")
public class Comment {

    @Schema(description = "id автора комментария")
    private int author;

    @Schema(description = "Ссылка на аватар автора комментария")
    private String authorImage;

    @Schema(description = "Имя автора комментария")
    private String authorFirstName;

    @Schema(description = "Время создания комментария")
    private long createdAt;

    @Schema(description = "id комментария")
    private int pk;

    @Schema(description = "Текст комментария")
    private String text;

}
