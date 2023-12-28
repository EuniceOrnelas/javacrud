package com.usersapi.endpoints.list;

import com.usersapi.domain.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "List all existing users with the GET method")
public class ListUserController {

    @Autowired
    ListUserService service;

    /**
     * Maneja la solicitud GET para listar todos los usuarios existentes.
     *
     * @return ResponseEntity con una lista de usuarios y el estado HTTP 200 OK si tiene éxito.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Execute GET method")
    public ResponseEntity<List<User>> listAllUsers_whenGetUsers() {
        List<User> userList = service.listAllUsers();
        return ResponseEntity.ok().body(userList);
    }
}
