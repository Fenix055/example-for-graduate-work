package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;

/**
 * Сервис для передачи данных пользователя из базы данных.
 */

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String cleanUsername = username != null ? username.toLowerCase() : "";

        UserModel userModel = userRepository.findByEmail(cleanUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + cleanUsername));

        String roleName = (userModel.getRole() != null) ? userModel.getRole().name() : "USER";

        return User.builder()
                .username(userModel.getEmail())
                .password(userModel.getPassword())
                .roles(roleName)
                .build();
    }

}
