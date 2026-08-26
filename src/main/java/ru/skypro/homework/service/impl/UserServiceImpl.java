package ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean setPassword(NewPassword newPassword, Authentication authentication) {
        log.info("Business logic: setting new password for user {}", authentication.getName());
        UserModel userModel = userRepository.findByEmail(authentication.getName()).orElse(null);
        if (userModel == null) {
            return false;
        }

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
        UserModel userModel = userRepository.findByEmail(authentication.getName()).orElse(null);
        return userMapper.toDto(userModel);
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        log.info("Business logic: updating info for user {}", authentication.getName());
        UserModel userModel = userRepository.findByEmail(authentication.getName()).orElse(null);
        if (userModel != null) {
            userMapper.updateModel(updateUser, userModel);
            userRepository.save(userModel);
        }
        return updateUser;
    }

    @Override
    public void updateUserImage(MultipartFile image, Authentication authentication) {
        log.info("Business logic: updating image for user {}", authentication.getName());
        UserModel userModel = userRepository.findByEmail(authentication.getName()).orElse(null);
        if (userModel != null) {
            userModel.setImage("/users/images/" + authentication.getName());
            userRepository.save(userModel);
        }
    }

}
