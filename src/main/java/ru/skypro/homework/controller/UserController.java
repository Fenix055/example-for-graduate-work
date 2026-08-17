package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        log.info("Request to update password");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        log.info("Request to update user info");
        UpdateUser dummyUpdate = new UpdateUser();
        return ResponseEntity.ok(dummyUpdate);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image) {
        log.info("Request to update user avatar");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        log.info("Request to get current user info");
        User dummyUser = new User();
        return ResponseEntity.ok(dummyUser);
    }


}
