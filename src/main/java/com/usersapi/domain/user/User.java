// Importación de las anotaciones Lombok para la generación automática de métodos como getters, setters, toString, equals, hashCode, etc.
package com.usersapi.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Importaciones de clases de persistencia de JPA
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

// Anotaciones Lombok para la generación de getters, setters, constructor sin argumentos y constructor con todos los argumentos, así como para generar métodos toString, equals y hashCode.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Indica que esta clase es una entidad JPA y debe ser mapeada a una tabla en la base de datos.
public class User implements Serializable {

    @Id // Indica que este campo es la clave primaria de la tabla en la base de datos.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de generación automática de valores para la clave primaria.
    private Long id; // Campo que representa el identificador único del usuario en la base de datos.

    private String name; // Campo que representa el nombre del usuario.
}
