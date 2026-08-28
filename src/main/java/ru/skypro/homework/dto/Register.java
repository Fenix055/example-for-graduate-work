package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для регистрации пользователя")
public class Register {

    @Schema(description = "Логин (почта)", example = "user@mail.ru")
    private String username;

    @Schema(description = "Пароль", example = "password")
    private String password;

    @Schema(description = "Имя", example = "Иван")
    private String firstName;

    @Schema(description = "Фамилия", example = "Иванов")
    private String lastName;

    @Schema(description = "Телефон в формате +7", example = "+7(123)456-78-90")
    private String phone;

    @Schema(description = "Роль пользователя")
    private Role role;

}
