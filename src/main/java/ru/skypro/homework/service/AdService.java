package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import org.springframework.security.core.Authentication;

import org.springframework.web.multipart.MultipartFile;

public interface AdService {

    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication);

    ExtendedAd getAdDetails(int id);

    void removeAd(int id);

    Ad updateAd(int id, CreateOrUpdateAd createOrUpdateAd);

    Ads getAdsMe(Authentication authentication);

    byte[] updateAdImage(int id, MultipartFile image);

}
