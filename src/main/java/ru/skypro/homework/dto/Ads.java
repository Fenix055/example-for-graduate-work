package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Список объявлений")
public class Ads {

    @Schema(description = "Общее количество объявлений")
    private int count;

    @Schema(description = "Список объявлений")
    private List<Ad> results;

}
