package ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;



@Slf4j
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    @Override
    public Ads getAllAds() {
        log.info("Business logic: getting all ads");
        List<AdModel> adModels = adRepository.findAll();

        Ads ads = new Ads();
        ads.setResults(adModels.stream().map(adMapper::toAdDto).collect(Collectors.toList()));
        ads.setCount(adModels.size());
        return ads;
    }

    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication) {
        log.info("Business logic: adding new ad by user {}", authentication.getName());
        UserModel author = userRepository.findByEmail(authentication.getName()).orElse(null);

        AdModel adModel = adMapper.toModel(properties);
        adModel.setAuthor(author);
        adModel.setImage("/ads/images/default.jpg");

        AdModel savedAd = adRepository.save(adModel);
        return adMapper.toAdDto(savedAd);
    }

    @Override
    public ExtendedAd getAdDetails(int id) {
        log.info("Business logic: getting ad details for id {}", id);
        AdModel adModel = adRepository.findById(id).orElse(null);
        return adMapper.toExtendedAdDto(adModel);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or " +
            "@adRepository.findById(#id).orElse(null) != null and " +
            "@adRepository.findById(#id).get().author.email == authentication.name")
    public void removeAd(int id) {
        log.info("Business logic: removing ad id {}", id);
        adRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or " +
            "@adRepository.findById(#id).orElse(null) != null and " +
            "@adRepository.findById(#id).get().author.email == authentication.name")
    public Ad updateAd(int id, CreateOrUpdateAd createOrUpdateAd) {
        log.info("Business logic: updating ad id {}", id);
        AdModel adModel = adRepository.findById(id).orElse(null);
        if (adModel != null) {
            adModel.setTitle(createOrUpdateAd.getTitle());
            adModel.setPrice(createOrUpdateAd.getPrice());
            adModel.setDescription(createOrUpdateAd.getDescription());
            adRepository.save(adModel);
        }
        return adMapper.toAdDto(adModel);
    }

    @Override
    public Ads getAdsMe(Authentication authentication) {
        log.info("Business logic: getting ads for current user {}", authentication.getName());
        List<AdModel> allAds = adRepository.findAll();

        List<AdModel> userAds = allAds.stream()
                .filter(ad -> ad.getAuthor() != null && ad.getAuthor().getEmail().equals(authentication.getName()))
                .collect(Collectors.toList());

        Ads ads = new Ads();
        ads.setResults(userAds.stream().map(adMapper::toAdDto).collect(Collectors.toList()));
        ads.setCount(userAds.size());
        return ads;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or " +
            "@adRepository.findById(#id).orElse(null) != null and " +
            "@adRepository.findById(#id).get().author.email == authentication.name")
    public byte[] updateAdImage(int id, MultipartFile image) {
        log.info("Business logic: updating image for ad id {}", id);
        return new byte[0];
    }

}
