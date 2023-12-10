package ru.semenenko.exception;

public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }
}
