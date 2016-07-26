package io.github.jokoframework.porandu.repositories;

import io.github.jokoframework.porandu.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionsRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findByLectureLectureId(Long pId);
}
