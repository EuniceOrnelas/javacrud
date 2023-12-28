package com.usersapi.endpoints.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usersapi.domain.user.User;
import com.usersapi.endpoints.detail.UserNotFoundException;
import com.usersapi.endpoints.update.UpdateUserController;
import com.usersapi.endpoints.update.UpdateUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Clase de prueba unitaria para el controlador UpdateUserController.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UpdateUserController.class)
public class UpdateUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UpdateUserService updateUserService;

    /**
     * Prueba para verificar la actualización de un usuario mediante el método PUT.
     *
     * @throws Exception Si ocurre un error durante la prueba.
     */
    @Test
    public void updateUser_whenPutUser() throws Exception {

        User user = new User();
        user.setName("Test Name");
        user.setId(89L);
        given(updateUserService.updateUser(user.getId(), user)).willReturn(user);

        ObjectMapper mapper = new ObjectMapper();

        // Realiza la petición PUT y verifica la respuesta
        mvc.perform(put("/users/" + user.getId().toString())
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(user.getName())));
    }

    /**
     * Prueba para verificar la excepción lanzada cuando el usuario no existe al actualizar.
     *
     * @throws Exception Si ocurre un error durante la prueba.
     */
    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws Exception {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        // Configuración para simular la excepción UserNotFoundException al actualizar el usuario
        Mockito.doThrow(new UserNotFoundException(user.getId())).when(updateUserService).updateUser(user.getId(), user);
        ObjectMapper mapper = new ObjectMapper();

        // Realiza la petición PUT y verifica la respuesta de error
        mvc.perform(put("/users/" + user.getId().toString())
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
