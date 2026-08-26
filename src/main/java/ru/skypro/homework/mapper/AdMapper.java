package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.dto.CreateOrUpdateAd;

import org.springframework.stereotype.Component;


@Component
public class AdMapper {

    public Ad toAdDto(AdModel model) {
        Ad dto = new Ad();

        if (model == null) {
            return dto;
        }

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
        ExtendedAd dto = new ExtendedAd();

        if (model == null) {
            return dto;
        }

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

    public AdModel toModel(CreateOrUpdateAd dto) {
        AdModel model = new AdModel();

        if (dto == null) {
            return model;
        }

        model.setTitle(dto.getTitle());
        model.setPrice(dto.getPrice());
        model.setDescription(dto.getDescription());
        return model;
    }

}
