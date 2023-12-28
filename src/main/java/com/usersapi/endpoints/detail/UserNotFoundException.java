package com.usersapi.endpoints.detail;

/**
 * Excepción personalizada para indicar que un usuario no fue encontrado.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor que recibe el ID del usuario no encontrado y genera un mensaje de error.
     *
     * @param id El ID del usuario que no fue encontrado.
     */
    public UserNotFoundException(Long id) {
        super("Could not find user with id " + id + ".");
    }
}
