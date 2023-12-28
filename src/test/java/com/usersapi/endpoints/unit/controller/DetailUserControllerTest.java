package com.usersapi.endpoints.unit.controller;

import com.usersapi.domain.user.User;
import com.usersapi.endpoints.detail.DetailUserController;
import com.usersapi.endpoints.detail.DetailUserService;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Clase de prueba unitaria para el controlador DetailUserController.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DetailUserController.class)
public class DetailUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DetailUserService detailUserService;

    /**
     * Prueba para verificar la obtención de un usuario por su ID mediante el método GET.
     *
     * @throws Exception Si ocurre un error durante la prueba.
     */
    @Test
    public void listUserById_whenGetMethod() throws Exception {

        User user = new User();
        user.setName("Test Name");
        user.setId(89L);

        // Configuración para simular la obtención exitosa del usuario
        given(detailUserService.listUser(user.getId())).willReturn(user);

        // Realiza la petición GET y verifica la respuesta
        mvc.perform(get("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(user.getName())));
    }

    /**
     * Prueba para verificar que se lance una excepción cuando el usuario no existe al intentar obtenerlo por su ID.
     *
     * @throws Exception Si ocurre un error durante la prueba.
     */
    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws Exception {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        // Configuración para simular el lanzamiento de una UserNotFoundException al intentar obtener un usuario que no existe
        Mockito.doThrow(new UserNotFoundException(user.getId())).when(detailUserService).listUser(user.getId());

        // Realiza la petición GET y verifica la respuesta de error 404 (Not Found)
        mvc.perform(get("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
