package ru.skypro.homework.service.impl;

import ru.skypro.homework.service.ImageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${images.dir-path}")
    private String imagesDirectory;

    @Override
    public String uploadImage(MultipartFile image, String dir) throws IOException {
        log.info("Uploading file to subdirectory: {}", dir);

        Path targetDir = Paths.get(imagesDirectory, dir);
        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir);
        }

        String extension = getExtension(image.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + extension;

        Path filePath = targetDir.resolve(fileName);
        Files.write(filePath, image.getBytes());

        return fileName;
    }

    @Override
    public byte[] getImage(String fileName, String dir) throws IOException {
        Path filePath = Paths.get(imagesDirectory, dir, fileName);
        if (Files.exists(filePath)) {
            return Files.readAllBytes(filePath);
        }
        return new byte[0];
    }


    private String getExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return ".jpg";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
