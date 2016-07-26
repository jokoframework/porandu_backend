package io.github.jokoframework.porandu.service.impl;

import io.github.jokoframework.porandu.entities.LectureEntity;
import io.github.jokoframework.porandu.entities.QuestionEntity;
import io.github.jokoframework.porandu.repositories.QuestionsRepository;
import io.github.jokoframework.porandu.service.QuestionService;
import io.github.jokoframework.porandu.web.dto.response.LectureResponseDTO;
import io.github.jokoframework.porandu.web.dto.response.PersonResponseDTO;
import io.github.jokoframework.porandu.web.dto.response.QuestionResponseDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by afeltes on 25/07/16.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public List<QuestionResponseDTO> findByLecture(Long pLectureId) {
        List<QuestionResponseDTO> questions = new ArrayList<>();
        List<QuestionEntity> questionEntities = questionsRepository.findByLectureLectureId(pLectureId);
        //TODO: Ver como se puede implementar este mapper: http://modelmapper.org/getting-started/
        if (CollectionUtils.isNotEmpty(questionEntities)) {
            for (QuestionEntity entity : questionEntities) {
                QuestionResponseDTO dto = new QuestionResponseDTO();
                BeanUtils.copyProperties(entity, dto);
                LectureResponseDTO lectureResponseDTO = new LectureResponseDTO();
                BeanUtils.copyProperties(entity.getLecture(), lectureResponseDTO);
                dto.setLecture(lectureResponseDTO);
                PersonResponseDTO personResponseDTO = new PersonResponseDTO();
                BeanUtils.copyProperties(entity.getAuthor(), personResponseDTO);
                dto.setAuthor(personResponseDTO);
                questions.add(dto);
            }
        }
        return questions;
    }
}
