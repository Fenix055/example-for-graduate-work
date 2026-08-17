package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        log.info("Request to get all ads");
        Ads dummyAds = new Ads();
        return ResponseEntity.ok(dummyAds);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile image){
        log.info("Request to add new ad");
        Ad dummyAd = new Ad();
        return ResponseEntity.status(HttpStatus.CREATED).body(dummyAd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable int id) {
        log.info("Request to get ad details by id: {}", id);
        ExtendedAd dummyExtendedAd = new ExtendedAd();
        return ResponseEntity.ok(dummyExtendedAd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable int id) {
        log.info("Request to delete ad by id: {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable int id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        log.info("Request to update ad by id: {}", id);
        Ad dummyAd = new Ad();
        return ResponseEntity.ok(dummyAd);
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {
        log.info("Request to get current user ads");
        Ads dummyAds = new Ads();
        return ResponseEntity.ok(dummyAds);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable int id,
                                              @RequestParam MultipartFile image) {
        log.info("Request to update ad image by id: {}", id);
        return ResponseEntity.ok(new byte[0]);
    }

}
