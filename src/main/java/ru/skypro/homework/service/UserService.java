package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import org.springframework.security.core.Authentication;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    boolean setPassword(NewPassword newPassword, Authentication authentication);

    User getUser(Authentication authentication);

    UpdateUser updateUser(UpdateUser updateUser, Authentication authentication);

    void updateUserImage(MultipartFile image, Authentication authentication);

}
