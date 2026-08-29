package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

/**
 * Реализация сервиса авторизации и регистрации пользователей.
 */

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final CustomUserDetailsServiceImpl userDetailsService;

    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           CustomUserDetailsServiceImpl userDetailsService) {
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean login(String userName, String password) {
        String cleanTypeName = userName != null ? userName.toLowerCase() : "";

        if (!userRepository.findByEmail(cleanTypeName).isPresent()) {
            return false;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(cleanTypeName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        String cleanEmail = register.getUsername().toLowerCase();
        if (userRepository.findByEmail(cleanEmail).isPresent()) {
            return false;
        }

        UserModel userModel = new UserModel();
        userModel.setEmail(cleanEmail);
        userModel.setPassword(this.encoder.encode(register.getPassword()));
        userModel.setFirstName(register.getFirstName());
        userModel.setLastName(register.getLastName());
        userModel.setPhone(register.getPhone());
        userModel.setRole(ru.skypro.homework.dto.Role.USER);

        userRepository.save(userModel);
        return true;
    }

}
