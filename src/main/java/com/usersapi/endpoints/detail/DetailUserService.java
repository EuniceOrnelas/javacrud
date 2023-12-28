package com.usersapi.endpoints.detail;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailUserService {

    @Autowired
    UserRepository repository;

    /**
     * Obtiene los detalles de un usuario existente por su ID.
     *
     * @param id El ID del usuario a obtener.
     * @return El usuario correspondiente al ID proporcionado.
     * @throws UserNotFoundException Si el usuario con el ID dado no existe.
     */
    public User listUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
