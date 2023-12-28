package com.usersapi.endpoints.unit.controller;

import com.usersapi.domain.user.User;
import com.usersapi.endpoints.create.CreateUserController;
import com.usersapi.endpoints.create.CreateUserService;
import com.usersapi.endpoints.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Clase de prueba unitaria para el controlador CreateUserController.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CreateUserController.class)
public class CreateUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserService service;

    /**
     * Prueba para verificar la creación de un usuario mediante el método POST.
     *
     * @throws Exception Si ocurre un error durante la prueba.
     */
    @Test
    public void createUser_whenPostMethod() throws Exception {
        // Configuración de un usuario de prueba
        User user = new User();
        user.setName("Test Name");

        // Configuración del servicio para devolver el usuario de prueba
        given(service.createNewUser(user)).willReturn(user);

        // Realiza la petición POST y verifica la creación del usuario
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(user.getName())));
    }
}
