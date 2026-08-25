package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

    @Operation(summary = "Получение всех объявлений", responses = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        log.info("Request to get all ads");
        Ads dummyAds = new Ads();
        return ResponseEntity.ok(dummyAds);
    }

    @io.swagger.v3.oas.annotations.Operation(
            summary = "Добавление объявления",
            description = "Принимает данные объявления и изображение в формате multipart/form-data",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@ModelAttribute CreateOrUpdateAd properties,
                                    @RequestParam("image") MultipartFile image) {
        log.info("Request to add new ad via multipart/form-data");
        Ad dummyAd = new Ad();
        return ResponseEntity.status(HttpStatus.CREATED).body(dummyAd);
    }

    @Operation(summary = "Получение информации об объявлении", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable int id) {
        log.info("Request to get ad details by id: {}", id);
        ExtendedAd dummyExtendedAd = new ExtendedAd();
        return ResponseEntity.ok(dummyExtendedAd);
    }

    @Operation(summary = "Удаление объявления", responses = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable int id) {
        log.info("Request to delete ad by id: {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Обновление информации об объявлении", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable int id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        log.info("Request to update ad by id: {}", id);
        Ad dummyAd = new Ad();
        return ResponseEntity.ok(dummyAd);
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {
        log.info("Request to get current user ads");
        Ads dummyAds = new Ads();
        return ResponseEntity.ok(dummyAds);
    }

    @Operation(summary = "Обновление картинки объявления", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable int id,
                                              @RequestParam MultipartFile image) {
        log.info("Request to update ad image by id: {}", id);
        return ResponseEntity.ok(new byte[0]);
    }

}
