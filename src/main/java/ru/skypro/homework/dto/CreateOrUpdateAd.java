package ru.skypro.homework.dto;


import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {

    @Schema(description = "Заголовок объявления", example = "Продам товар")
    private String title;

    @Schema(description = "Цена объявления", example = "123")
    private int price;

    @Schema(description = "Описание объявления", example = "Самый товарный товар из всех товаров")
    private String description;

}
