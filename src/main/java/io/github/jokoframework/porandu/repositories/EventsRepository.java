package io.github.jokoframework.porandu.repositories;

import io.github.jokoframework.porandu.entities.EventEntity;
import io.github.jokoframework.porandu.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventsRepository extends JpaRepository<EventEntity, Long> {

}
