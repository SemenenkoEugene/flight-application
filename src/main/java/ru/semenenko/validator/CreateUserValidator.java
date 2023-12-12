package ru.semenenko.validator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.semenenko.dto.CreateUserDto;
import ru.semenenko.entity.Gender;
import ru.semenenko.entity.Role;
import ru.semenenko.util.LocalDateFormatter;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    public ValidationResult isValid(CreateUserDto userDto) {
        ValidationResult validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(userDto.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }
        if (userDto.getEmail().isEmpty()) {
            validationResult.add(Error.of("invalid.email", "Email is invalid"));
        }
        if (userDto.getName().isEmpty()) {
            validationResult.add(Error.of("invalid.name", "Name is invalid"));
        }
        if (Gender.find(userDto.getGender()).isEmpty()) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        if (Role.find(userDto.getRole()).isEmpty()) {
            validationResult.add(Error.of("role.invalid", "Role is invalid"));
        }
        if (userDto.getPassword().isEmpty()) {
            validationResult.add(Error.of("password.invalid", "Password is invalid"));
        }
        return validationResult;
    }
}
