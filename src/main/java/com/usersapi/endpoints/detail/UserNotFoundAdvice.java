package com.usersapi.endpoints.detail;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotFoundAdvice {

    /**
     * Manejador para la excepción UserNotFoundException.
     * Devuelve un mensaje de error con el estado HTTP 404 (NOT_FOUND).
     *
     * @param ex La excepción UserNotFoundException lanzada.
     * @return Mensaje de error indicando que el usuario no fue encontrado.
     */
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }
}
