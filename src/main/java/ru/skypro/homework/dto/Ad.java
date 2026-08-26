package ru.skypro.homework.dto;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Информация об объявлении")
public class Ad {

    @Schema(description = "id автора объявления")
    private int author;

    @Schema(description = "ссылка на изображение объявления")
    private String image;

    @Schema(description = "id объявления")
    private int pk;

    @Schema(description = "цена объявления")
    private int price;

    @Schema(description = "заголовок объявления")
    private String title;

}
