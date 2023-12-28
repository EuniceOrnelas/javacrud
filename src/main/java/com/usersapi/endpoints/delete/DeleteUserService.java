package com.usersapi.endpoints.delete;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.detail.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio que gestiona la eliminación de un usuario existente.
 */
@Service
public class DeleteUserService {

    @Autowired
    UserRepository repository;

    /**
     * Elimina un usuario existente por su ID.
     *
     * @param id El ID del usuario a ser eliminado.
     * @throws UserNotFoundException Si el usuario no se encuentra por el ID proporcionado.
     */
    public void deleteUser(Long id) {

        // Busca el usuario por su ID en el repositorio
        repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)); // Lanza una excepción si no se encuentra el usuario

        // Elimina el usuario por su ID
        repository.deleteById(id);
    }
}
