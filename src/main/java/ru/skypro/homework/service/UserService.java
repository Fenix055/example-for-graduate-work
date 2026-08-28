package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

/**
 * Сервис для управления профилями пользователей и их учетными данными.
 */

public interface UserService {

    /**
     * Изменяет текущий пароль пользователя на новый с предварительной проверкой старого пароля.
     *
     * @param newPassword    DTO с текущим и новым паролем
     * @param authentication объект авторизации текущего пользователя
     * @return true, если пароль успешно изменен, иначе false
     */
    boolean setPassword(NewPassword newPassword, Authentication authentication);

    /**
     * Получает полную информацию о текущем авторизованном пользователе.
     *
     * @param authentication объект авторизации текущего пользователя
     * @return DTO с данными пользователя
     */
    User getUser(Authentication authentication);

    /**
     * Обновляет личные данные пользователя (имя, фамилию, телефон).
     *
     * @param updateUser     DTO с обновляемыми данными
     * @param authentication объект авторизации текущего пользователя
     * @return DTO с обновленными данными
     */
    UpdateUser updateUser(UpdateUser updateUser, Authentication authentication);


    /**
     * Загружает и обновляет аватар текущего пользователя.
     *
     * @param image          файл изображения аватара
     * @param authentication объект авторизации текущего пользователя
     */
    void updateUserImage(MultipartFile image, Authentication authentication);

}
