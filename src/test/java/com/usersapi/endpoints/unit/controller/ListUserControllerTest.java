package com.usersapi.endpoints.unit.controller;

import com.usersapi.domain.user.User;
import com.usersapi.endpoints.list.ListUserController;
import com.usersapi.endpoints.list.ListUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Clase de prueba unitaria para el controlador ListUserController.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ListUserController.class)
public class ListUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ListUserService listUserService;

    /**
     * Prueba para verificar la obtención de todos los usuarios mediante el método GET.
     *
     * @throws Exception Si ocurre un error durante la prueba.
     */
    @Test
    public void listAllUsers_whenGetMethod() throws Exception {

        User user = new User();
        user.setName("Test name");

        List<User> allUsers = Arrays.asList(user);

        // Configuración para simular la obtención exitosa de todos los usuarios
        given(listUserService.listAllUsers()).willReturn(allUsers);

        // Realiza la petición GET y verifica la respuesta
        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(user.getName())));
    }
}
