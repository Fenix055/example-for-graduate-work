package ru.skypro.homework.dto;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Данные для создания или обновления комментария")
public class CreateOrUpdateComment {

    @Schema(description = "Текст комментария", example = "Рекомендую", requiredMode = Schema.RequiredMode.REQUIRED)
    private String text;

}
