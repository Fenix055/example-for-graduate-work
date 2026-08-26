package ru.skypro.homework.dto;


import lombok.Data;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Список комментариев")
public class Comments {

    @Schema(description = "Общее количество комментариев")
    private int count;

    @Schema(description = "Коллекция комментариев")
    private List<Comment> results;

}
