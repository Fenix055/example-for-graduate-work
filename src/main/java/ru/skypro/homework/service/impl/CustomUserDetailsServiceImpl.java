package ru.skypro.homework.service.impl;

import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsManager {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String cleanUsername = username != null ? username.toLowerCase() : ""; //Do Not Feed the Monkeys

        UserModel userModel = userRepository.findByEmail(cleanUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + cleanUsername));

        String roleName = (userModel.getRole() != null) ? userModel.getRole().name() : "USER";

        return User.builder()
                .username(userModel.getEmail())
                .password(userModel.getPassword())
                .roles(roleName)
                .build();
    }

    @Override
    public void createUser(UserDetails user) {
        UserModel userModel = new UserModel();
        userModel.setEmail(user.getUsername() != null ? user.getUsername().toLowerCase() : "");//Do Not Feed the Monkeys
        userModel.setPassword(user.getPassword());
        userModel.setRole(ru.skypro.homework.dto.Role.USER);//Защита от выдачи прав администратора
        userRepository.save(userModel);
    }

    @Override
    public boolean userExists(String username) {
        String cleanUsername = username != null ? username.toLowerCase() : "";//Do Not Feed the Monkeys
        return userRepository.findByEmail(cleanUsername).isPresent();
    }

    @Override
    public void updateUser(UserDetails user) {}

    @Override
    public void deleteUser(String username) {}

    @Override
    public void changePassword(String oldPassword, String newPassword) {}

}
