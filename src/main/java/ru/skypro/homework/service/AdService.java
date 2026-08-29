package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

/**
 * Сервис для управления объявлениями торговой площадки.
 */

public interface AdService {

    /**
     * Получает список всех существующих объявлений.
     *
     * @return объект Ads со списком объявлений и их количеством
     */
    Ads getAllAds();


    /**
     * Создает новое объявление и прикрепляет к нему изображение.
     */
    Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication);


    /**
     * Получает детальную информацию об объявлении по его идентификатору.
     */
    ExtendedAd getAdDetails(int id);


    /**
     * Удаляет объявление и связанный с ним файл изображения с диска.
     */
    void removeAd(int id);


    /**
     * Обновляет текстовые данные объявления (заголовок, цену, описание).
     */
    Ad updateAd(int id, CreateOrUpdateAd createOrUpdateAd);


    /**
     * Получает список всех объявлений текущего авторизованного пользователя.
     */
    Ads getAdsMe(Authentication authentication);


    /**
     * Обновляет или заменяет изображение существующего объявления.
     */
    byte[] updateAdImage(int id, MultipartFile image);

}
