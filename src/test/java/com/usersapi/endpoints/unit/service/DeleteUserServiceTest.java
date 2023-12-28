package com.usersapi.endpoints.unit.service;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.delete.DeleteUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Clase de prueba unitaria para el servicio DeleteUserService.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserService deleteUserService;

    /**
     * Prueba para verificar la eliminación de un usuario existente.
     */
    @Test
    public void whenGivenId_shouldDeleteUser_ifFound(){
        // Configuración del usuario de prueba
        User user = new User();
        user.setName("Test Name");
        user.setId(1L);

        // Mock del método findById del repositorio para devolver el usuario
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Ejecución del método deleteUser del servicio
        deleteUserService.deleteUser(user.getId());

        // Verificación de la eliminación del usuario por su ID
        verify(userRepository).deleteById(user.getId());
    }

    /**
     * Prueba para verificar el manejo de excepciones cuando el usuario no existe.
     */
    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_user_doesnt_exist() {
        // Configuración del usuario de prueba
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        // Mock del método findById del repositorio para devolver null (usuario no encontrado)
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));

        // Ejecución del método deleteUser del servicio que debería lanzar una excepción
        deleteUserService.deleteUser(user.getId());
    }
}
