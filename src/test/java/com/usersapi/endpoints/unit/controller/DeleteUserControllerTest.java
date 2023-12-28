package com.usersapi.endpoints.unit.controller;

import com.usersapi.domain.user.User;
import com.usersapi.endpoints.delete.DeleteUserController;
import com.usersapi.endpoints.delete.DeleteUserService;
import com.usersapi.endpoints.detail.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Clase de prueba unitaria para el controlador DeleteUserController.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DeleteUserController.class)
public class DeleteUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DeleteUserService deleteUserService;

    /**
     * Prueba para verificar la eliminación de un usuario mediante el método DELETE.
     *
     * @throws Exception Si ocurre un error durante la prueba.
     */
    @Test
    public void removeUserById_whenDeleteMethod() throws Exception {
        User user = new User();
        user.setName("Test Name");
        user.setId(89L);

        // Configuración para simular la eliminación exitosa del usuario
        doNothing().when(deleteUserService).deleteUser(user.getId());

        // Realiza la petición DELETE y verifica la respuesta
        mvc.perform(delete("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    /**
     * Prueba para verificar que se lance una excepción cuando el usuario no existe al intentar eliminarlo.
     *
     * @throws Exception Si ocurre un error durante la prueba.
     */
    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws Exception {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        // Configuración para simular el lanzamiento de una UserNotFoundException al intentar eliminar un usuario que no existe
        Mockito.doThrow(new UserNotFoundException(user.getId())).when(deleteUserService).deleteUser(user.getId());

        // Realiza la petición DELETE y verifica la respuesta de error 404 (Not Found)
        mvc.perform(delete("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
