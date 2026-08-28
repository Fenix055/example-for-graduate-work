package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Реализация сервиса для сохранения и чтения изображений на диске.
 */

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${images.dir-path}")
    private String imagesDirectory;

    @Override
    public String uploadImage(MultipartFile image, String dir) throws IOException {
        log.info("Uploading file to subdirectory: {}", dir);

        String contentType = image.getContentType();
        if (contentType == null || (!contentType.equals("image/jpeg") && !contentType.equals("image/png"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Разрешены только форматы JPEG и PNG");
        }

        Path targetDir = Paths.get(imagesDirectory, dir);
        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir);
        }

        String extension = contentType.equals("image/png") ? ".png" : ".jpg";
        String fileName = UUID.randomUUID().toString() + extension;

        Path filePath = targetDir.resolve(fileName);
        Files.write(filePath, image.getBytes());

        return fileName;
    }

    @Override
    public byte[] getImage(String fileName, String dir) throws IOException {

        Path filePath = Paths.get(imagesDirectory, dir).resolve(fileName).normalize();
        if (!filePath.startsWith(Paths.get(imagesDirectory).toAbsolutePath().normalize())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Доступ запрещен");
        }

        if (!Files.exists(filePath)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Изображение не найдено на сервере");
        }

        return Files.readAllBytes(filePath);
    }

}
