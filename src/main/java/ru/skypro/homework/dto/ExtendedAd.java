package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Расширенная информация об объявлении")
public class ExtendedAd {

    @Schema(description = "id объявления")
    private int pk;

    @Schema(description = "Имя автора объявления")
    private String authorFirstName;

    @Schema(description = "Фамилия автора объявления")
    private String authorLastName;

    @Schema(description = "Описание объявления")
    private String description;

    @Schema(description = "Почта автора объявления")
    private String email;

    @Schema(description = "Ссылка на изображение объявления")
    private String image;

    @Schema(description = "Телефон автора объявления")
    private String phone;

    @Schema(description = "Цена объявления")
    private int price;

    @Schema(description = "Заголовок объявления")
    private String title;

}
