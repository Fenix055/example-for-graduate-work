package ru.skypro.homework.dto;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Данные для смены пароля")
public class NewPassword {

    @Schema(description = "Текущий пароль")
    private String currentPassword;

    @Schema(description = "Новый пароль")
    private String newPassword;

}
