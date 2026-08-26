package ru.skypro.homework.dto;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Данные для обновления профиля пользователя")
public class UpdateUser {

    @Schema(description = "Имя пользователя", example = "Иван")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    private String lastName;

    @Schema(description = "Телефон пользователя", example = "+7(123)456-78-90")
    private String phone;

}
