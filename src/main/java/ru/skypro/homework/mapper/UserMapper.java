package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserModel;

@Component
public class UserMapper {

    public User toDto(UserModel model) {
        User dto = new User();
        if (model == null) {
            return dto;
        }
        dto.setId(model.getId());
        dto.setEmail(model.getEmail());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setPhone(model.getPhone());
        dto.setRole(model.getRole());
        dto.setImage(model.getImage());
        return dto;
    }

    public UserModel toModel(Register registerDto) {
        UserModel model = new UserModel();
        if (registerDto == null) {
            return model;
        }

        model.setEmail(registerDto.getUsername());
        model.setPassword(registerDto.getPassword());
        model.setFirstName(registerDto.getFirstName());
        model.setLastName(registerDto.getLastName());
        model.setPhone(registerDto.getPhone());
        model.setRole(registerDto.getRole());
        return model;
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
