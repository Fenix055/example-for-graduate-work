package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

/**
 * Реализация сервиса для управления профилями пользователей.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    @Override
    public boolean setPassword(NewPassword newPassword, Authentication authentication) {
        log.info("Business logic: setting new password for user {}", authentication.getName());
        UserModel userModel = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (passwordEncoder.matches(newPassword.getCurrentPassword(), userModel.getPassword())) {
            userModel.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
            userRepository.save(userModel);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(Authentication authentication) {
        log.info("Business logic: getting info for user {}", authentication.getName());
        UserModel userModel = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.toDto(userModel);
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        log.info("Business logic: updating info for user {}", authentication.getName());
        UserModel userModel = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userMapper.updateModel(updateUser, userModel);
        userRepository.save(userModel);
        return updateUser;
    }

    @Override
    public void updateUserImage(MultipartFile image, Authentication authentication) {
        log.info("Business logic: updating image for user {}", authentication.getName());
        UserModel userModel = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        try {
            String fileName = imageService.uploadImage(image, "users");
            userModel.setImage("/images/users/" + fileName);
            userRepository.save(userModel);
        } catch (java.io.IOException e) {
            log.error("Failed to upload user avatar", e);
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save avatar");
        }
    }

}
