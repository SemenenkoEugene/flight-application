package ru.semenenko.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
