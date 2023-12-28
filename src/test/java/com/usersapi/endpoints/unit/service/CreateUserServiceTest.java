package com.usersapi.endpoints.unit.service;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.create.CreateUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Clase de prueba unitaria para el servicio CreateUserService.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserService createUserService;

    /**
     * Prueba para verificar la creación de un usuario y su almacenamiento.
     */
    @Test
    public void whenSaveUser_shouldReturnUser() {
        // Configuración de datos de usuario de prueba
        User user = new User();
        user.setName("Test Name");

        // Mock del método save del repositorio para devolver el usuario creado
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        // Ejecución del método createNewUser del servicio
        User created = createUserService.createNewUser(user);

        // Verificación de la creación del usuario y el almacenamiento en el repositorio
        assertThat(created.getName()).isSameAs(user.getName());
        verify(userRepository).save(user);
    }
}
