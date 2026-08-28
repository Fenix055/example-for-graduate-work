package ru.skypro.homework.controller;

import ru.skypro.homework.service.ImageService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class FileController {

    private final ImageService imageService;

    @Operation(summary = "Получение изображения с диска сервера из конкретной папки")
    @GetMapping(value = "/{dir}/{fileName}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE
    })
    public byte[] getImage(@PathVariable String dir, @PathVariable String fileName) {
        log.info("Request to download file: {} from directory: {}", fileName, dir);
        try {
            return imageService.getImage(fileName, dir);
        } catch (IOException e) {
            log.error("Error while reading file: {} from {}", fileName, dir, e);
            return new byte[0];
        }
    }

}
