package io.github.jokoframework.porandu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jokoframework.porandu.entities.ParametersEntity;


public interface ParametersRepository extends JpaRepository<ParametersEntity, Long> {

	ParametersEntity findByName(String pName);

}
