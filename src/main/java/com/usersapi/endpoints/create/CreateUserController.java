package com.usersapi.endpoints.create;

import com.usersapi.domain.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@Api(tags = "Create a new user with the POST method")
public class CreateUserController {

    @Autowired
    CreateUserService service;

    // Maneja las solicitudes POST para crear un nuevo usuario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Execute POST method")
    public ResponseEntity<User> createNewUser_whenPostUser(@RequestBody User user) {

        // Invoca el servicio para crear un nuevo usuario
        User createdUser = service.createNewUser(user);

        // Construye la URI para la respuesta, incluyendo el ID del nuevo usuario creado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        // Retorna una respuesta con el código de estado 201 (CREATED) y la información del nuevo usuario creado
        return ResponseEntity.created(uri).body(createdUser);
    }
}
