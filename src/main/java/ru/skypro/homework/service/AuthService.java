package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Сервис для управления процессами аутентификации и регистрации пользователей.
 */

public interface AuthService {

    /**
     * Проверяет учетные данные пользователя при входе в систему.
     *
     * @param userName логин пользователя (email)
     * @param password введенный пользователем чистый пароль
     * @return true, если логин существует и хэш пароля совпал, иначе false
     */
    boolean login(String userName, String password);


    /**
     * Регистрирует нового пользователя в системе с проверкой уникальности логина.
     *
     * @param register DTO с регистрационными данными пользователя
     * @return true, если регистрация прошла успешно, false если пользователь уже существует
     */
    boolean register(Register register);
}
