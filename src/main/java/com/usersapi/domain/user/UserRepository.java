// Importaciones de clases necesarias de Spring Framework
package com.usersapi.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Anotación que marca esta interfaz como un componente de repositorio de Spring.
public interface UserRepository extends JpaRepository<User, Long> {
}
