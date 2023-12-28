package com.usersapi.endpoints.unit.service;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.update.UpdateUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Clase de prueba unitaria para el servicio UpdateUserService.
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserService updateUserService;

    /**
     * Prueba para verificar la actualización de un usuario cuando se encuentra.
     */
    @Test
    public void whenGivenId_shouldUpdateUser_ifFound() {
        // Configuración de usuario existente y usuario con datos actualizados
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        User newUser = new User();
        newUser.setName("New Test Name");

        // Simulación de búsqueda de usuario existente por ID y actualización
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        updateUserService.updateUser(user.getId(), newUser);

        // Verificación de que se haya guardado el usuario actualizado y se haya buscado el usuario existente por ID
        verify(userRepository).save(newUser);
        verify(userRepository).findById(user.getId());
    }

    /**
     * Prueba para verificar la excepción cuando el usuario no existe al intentar actualizarlo.
     */
    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_user_doesnt_exist() {
        // Configuración de usuario no existente y usuario con datos actualizados
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        User newUser = new User();
        newUser.setId(90L);
        newUser.setName("New Test Name");

        // Simulación de búsqueda de usuario inexistente por ID y actualización
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        updateUserService.updateUser(user.getId(), newUser);
    }
}
