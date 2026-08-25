package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdModel;

import org.springframework.stereotype.Component;


@Component
public class AdMapper {

    public Ad toAdDto(AdModel model) {
        if (model == null) {
            return null;
        }

        Ad dto = new Ad();

        dto.setPk(model.getPk());
        dto.setPrice(model.getPrice());
        dto.setTitle(model.getTitle());
        dto.setImage(model.getImage());

        if (model.getAuthor() != null) {
            dto.setAuthor(model.getAuthor().getId());
        }
        return dto;
    }

    public ExtendedAd toExtendedAdDto(AdModel model) {
        if (model == null) {
            return null;
        }

        ExtendedAd dto = new ExtendedAd();

        dto.setPk(model.getPk());
        dto.setPrice(model.getPrice());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());
        dto.setImage(model.getImage());

        if (model.getAuthor() != null) {
            dto.setAuthorFirstName(model.getAuthor().getFirstName());
            dto.setAuthorLastName(model.getAuthor().getLastName());
            dto.setEmail(model.getAuthor().getEmail());
            dto.setPhone(model.getAuthor().getPhone());
        }
        return dto;
    }

}
