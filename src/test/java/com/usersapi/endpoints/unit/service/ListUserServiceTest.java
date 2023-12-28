package com.usersapi.endpoints.unit.service;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.list.ListUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Clase de prueba unitaria para el servicio ListUserService.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListUserService listUserService;

    /**
     * Prueba para verificar que se devuelvan todos los usuarios correctamente.
     */
    @Test
    public void shouldReturnAllUsers() {
        // Configuración de la lista de usuarios de prueba
        List<User> users = new ArrayList();
        users.add(new User());

        // Mock del método findAll del repositorio para devolver la lista de usuarios
        given(userRepository.findAll()).willReturn(users);

        // Ejecución del método listAllUsers del servicio
        List<User> expected = listUserService.listAllUsers();

        // Verificación de que se devuelvan todos los usuarios esperados
        assertEquals(expected, users);
        verify(userRepository).findAll();
    }
}
