package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.model.UserModel;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDto(UserModel model) {
        if (model == null) {
            return null;
        }
        User dto = new User();
        dto.setId(model.getId());
        dto.setEmail(model.getEmail());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setPhone(model.getPhone());
        dto.setRole(model.getRole());
        dto.setImage(model.getImage());
        return dto;
    }

    public void updateModel(UpdateUser dto, UserModel model) {
        if (dto == null || model == null) {
            return;
        }
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setPhone(dto.getPhone());
    }

}
