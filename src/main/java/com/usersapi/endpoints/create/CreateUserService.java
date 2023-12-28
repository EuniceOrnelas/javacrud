package com.usersapi.endpoints.create;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona la creación de un nuevo usuario.
 */
@Service
public class CreateUserService {

    @Autowired
    UserRepository repository;

    /**
     * Crea un nuevo usuario y lo guarda en la base de datos.
     *
     * @param user El usuario a ser creado.
     * @return El usuario creado y guardado en la base de datos.
     */
    public User createNewUser(User user) {
        return repository.save(user);
    }
}
