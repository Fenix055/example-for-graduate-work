package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для создания или обновления комментария")
public class CreateOrUpdateComment {

    @Schema(description = "Текст комментария", example = "Рекомендую", requiredMode = Schema.RequiredMode.REQUIRED)
    private String text;

}
