package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Cервис для сохранения медиафайлов на сервере и их чтения.
 */

public interface ImageService {

    /**
     * Сохраняет изображение в указанную поддиректорию на диске сервера.
     *
     * @param image загружаемый файл изображения
     * @param dir   название целевой поддиректории (например, "ads" или "users")
     * @return сгенерированное уникальное имя файла с расширением
     * @throws IOException при ошибках ввода-вывода при записи на диск
     */
    String uploadImage(MultipartFile image, String dir) throws IOException;

    /**
     * Считывает файл изображения с диска сервера.
     *
     * @param fileName имя файла на диске
     * @param dir      название поддиректории, где хранится файл
     * @return массив байт изображения
     * @throws IOException при ошибках чтения файла с диска
     */
    byte[] getImage(String fileName, String dir) throws IOException;
}
