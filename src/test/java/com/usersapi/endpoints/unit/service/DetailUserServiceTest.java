package com.usersapi.endpoints.unit.service;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.detail.DetailUserService;
import com.usersapi.endpoints.detail.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Clase de prueba unitaria para el servicio DetailUserService.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DetailUserService detailUserService;

    /**
     * Prueba para verificar que se devuelve un usuario cuando se proporciona un ID existente.
     */
    @Test
    public void whenGivenId_shouldReturnUser_ifFound() {
        // Configuración del usuario de prueba
        User user = new User();
        user.setId(89L);

        // Mock del método findById del repositorio para devolver el usuario
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Ejecución del método listUser del servicio
        User expected = detailUserService.listUser(user.getId());

        // Verificación de que se devuelve el usuario esperado
        assertThat(expected).isSameAs(user);
        verify(userRepository).findById(user.getId());
    }

    /**
     * Prueba para verificar el manejo de excepciones cuando el usuario no existe.
     */
    @Test(expected = UserNotFoundException.class)
    public void should_throw_exception_when_user_doesnt_exist() {
        // Configuración del usuario de prueba
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        // Mock del método findById del repositorio para devolver null (usuario no encontrado)
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));

        // Ejecución del método listUser del servicio que debería lanzar una excepción
        detailUserService.listUser(user.getId());
    }
}
