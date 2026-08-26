package ru.skypro.homework.dto;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Данные для авторизации пользователя")
public class Login {

    @Schema(description = "Логин (почта пользователя)", example = "user@mail.ru")
    private String username;

    @Schema(description = "Пароль пользователя", example = "password")
    private String password;

}
