package io.github.jokoframework.porandu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jokoframework.porandu.entities.ParametroEntity;


public interface ParametrosRepository extends JpaRepository<ParametroEntity, Long> {

	ParametroEntity findByNombre(String nombre);

}
