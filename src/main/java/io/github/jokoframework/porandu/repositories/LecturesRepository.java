package io.github.jokoframework.porandu.repositories;

import io.github.jokoframework.porandu.entities.EventEntity;
import io.github.jokoframework.porandu.entities.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LecturesRepository extends JpaRepository<LectureEntity, Long> {

    List<LectureEntity> findByEvent(EventEntity pEventEntity);
}
