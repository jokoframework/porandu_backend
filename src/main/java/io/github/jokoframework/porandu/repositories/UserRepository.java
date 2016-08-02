package io.github.jokoframework.porandu.repositories;

import io.github.jokoframework.porandu.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author agimenez
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
}
