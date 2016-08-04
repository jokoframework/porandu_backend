package io.github.jokoframework.porandu.repositories;

import io.github.jokoframework.porandu.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author afeltes
 */
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    PersonEntity findByEmail(String pEmail);
}
