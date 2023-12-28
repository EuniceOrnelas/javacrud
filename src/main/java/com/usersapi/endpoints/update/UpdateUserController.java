package com.usersapi.endpoints.update;

import com.usersapi.domain.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{id}")
@Api(tags = "Update an existing user with the PUT method")
public class UpdateUserController {

    @Autowired
    UpdateUserService service;

    /**
     * Actualiza un usuario existente utilizando el método PUT.
     *
     * @param user Los datos actualizados del usuario.
     * @param id   El ID del usuario a actualizar.
     * @return ResponseEntity con el usuario actualizado y el código de estado OK.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Execute PUT method")
    public ResponseEntity<User> updateUser_whenPutUser(@RequestBody User user, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.updateUser(id, user));
    }
}
