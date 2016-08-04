package io.github.jokoframework.porandu.service.impl;

import io.github.jokoframework.porandu.entities.EventEntity;
import io.github.jokoframework.porandu.entities.LectureEntity;
import io.github.jokoframework.porandu.entities.QuestionEntity;
import io.github.jokoframework.porandu.entities.VoteEntity;
import io.github.jokoframework.porandu.repositories.LecturesRepository;
import io.github.jokoframework.porandu.repositories.QuestionsRepository;
import io.github.jokoframework.porandu.repositories.VotesRepository;
import io.github.jokoframework.porandu.service.LecturesService;
import io.github.jokoframework.porandu.web.dto.response.EventResponseDTO;
import io.github.jokoframework.porandu.web.dto.response.LectureResponseDTO;
import io.github.jokoframework.porandu.web.dto.response.PersonResponseDTO;
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
public class LecturesServiceImpl implements LecturesService {

    @Autowired
    private LecturesRepository lecturesRepository;
    
    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private VotesRepository votesRepository;
    
    @Override
    public List<LectureResponseDTO> findByEventId(Long pEventId) {
        List<LectureResponseDTO> questions = new ArrayList<>();
        List<LectureEntity> eventEntities = lecturesRepository.findByEvent(new EventEntity(pEventId));
        //TODO: Ver como se puede implementar este mapper: http://modelmapper.org/getting-started/
        if (CollectionUtils.isNotEmpty(eventEntities)) {
            for (LectureEntity entity : eventEntities) {
                LectureResponseDTO dto = new LectureResponseDTO();
                BeanUtils.copyProperties(entity, dto);
                EventResponseDTO event = new EventResponseDTO();
                BeanUtils.copyProperties(entity.getEvent(), event);
                dto.setEvent(event);
                PersonResponseDTO personResponseDTO = new PersonResponseDTO();
                BeanUtils.copyProperties(entity.getAuthor(), personResponseDTO);
                dto.setAuthor(personResponseDTO);
                getTotalQuestionAndVotes(dto);
                questions.add(dto);
            }
        }
        return questions;
    }

	private void getTotalQuestionAndVotes(LectureResponseDTO dto) {
		Integer totalQuestion = 0;
		Integer totalVotes = 0;
		List<QuestionEntity> questionsEntities = questionsRepository.findByLectureLectureId(dto.getLectureId());
		if (CollectionUtils.isNotEmpty(questionsEntities)) {
			totalQuestion = questionsEntities.size();
			for(QuestionEntity question:questionsEntities){
				List<VoteEntity> votes = votesRepository.findAllByQuestion(question);
				totalVotes = totalVotes + votes.size();
			}
		}
		dto.setTotalQuestion(totalQuestion);
		dto.setTotalVotes(totalVotes);
		
	}
}
