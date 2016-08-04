package io.github.jokoframework.porandu.service.impl;

import io.github.jokoframework.porandu.entities.*;
import io.github.jokoframework.porandu.exceptions.PoranduValueRequiredException;
import io.github.jokoframework.porandu.repositories.LecturesRepository;
import io.github.jokoframework.porandu.repositories.PersonRepository;
import io.github.jokoframework.porandu.repositories.QuestionsRepository;
import io.github.jokoframework.porandu.repositories.VotesRepository;
import io.github.jokoframework.porandu.service.QuestionsService;
import io.github.jokoframework.porandu.service.UserService;
import io.github.jokoframework.porandu.web.dto.request.QuestionRequestDTO;
import io.github.jokoframework.porandu.web.dto.response.LectureResponseDTO;
import io.github.jokoframework.porandu.web.dto.response.PersonResponseDTO;
import io.github.jokoframework.porandu.web.dto.response.QuestionResponseDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by afeltes on 25/07/16.
 */
@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private VotesRepository voteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LecturesRepository lectureRepository;

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

    @Override
    public QuestionResponseDTO findById(Long questionId) {
        QuestionEntity question = questionsRepository.getOne(questionId);
        QuestionResponseDTO dto = new QuestionResponseDTO();
        BeanUtils.copyProperties(question, dto);
        return dto;
    }

    @Override
    @Transactional
    public QuestionResponseDTO vote(QuestionResponseDTO question, String userName) {
        UserEntity user = userService.getOrInsertUser(userName);
        Long questionId = question.getQuestionId();
        VoteEntity vote = voteRepository.getByQuestionQuestionIdAndUserUserId(questionId, user.getId());
        if (vote == null) {
            vote = new VoteEntity();
            QuestionEntity questionEntity = questionsRepository.getOne(questionId);
            vote.setQuestion(questionEntity);
            vote.setUser(user);
            voteRepository.save(vote);
        }
        question.setVoted(true);
        question.setVotes(voteRepository.countByQuestionQuestionId(questionId));
        return question;
    }

    @Override
    @Transactional
    public QuestionResponseDTO downvote(QuestionResponseDTO question, String userName) {
        UserEntity user = userService.getOrInsertUser(userName);
        Long questionId = question.getQuestionId();
        VoteEntity vote = voteRepository.getByQuestionQuestionIdAndUserUserId(questionId, user.getId());
        if (vote != null) {
            voteRepository.delete(vote);
        }
        question.setVoted(false);
        question.setVotes(voteRepository.countByQuestionQuestionId(questionId));
        return question;
    }

    @Override
    public List<QuestionResponseDTO> getVotes(List<QuestionResponseDTO> questions, String userName) {
        UserEntity user = userService.getOrInsertUser(userName);
        List<VoteEntity> votes = voteRepository.findByUserUserId(user.getId());
        List<Long> questionList = new ArrayList<>();
        for (VoteEntity vote : votes) {
            questionList.add(vote.getQuestion().getId());
        }

        for (QuestionResponseDTO dto : questions) {
            if (questionList.contains(dto.getQuestionId())) {
                dto.setVoted(true);
            }
            dto.setVotes(voteRepository.countByQuestionQuestionId(dto.getQuestionId()));
        }
        return questions;
    }

    @Override
    public QuestionResponseDTO save(QuestionRequestDTO pQuestionRequestDTO, String pUserName) {
        UserEntity user = userService.getOrInsertUser(pUserName);
        if (StringUtils.isBlank(pQuestionRequestDTO.getEmail()) &&
                StringUtils.isBlank(pQuestionRequestDTO.getTitle())) {
            throw new PoranduValueRequiredException("Email y título/pregunta son requeridos");
        }
        PersonEntity authorEntity = new PersonEntity();
        authorEntity.setEmail(pQuestionRequestDTO.getEmail());
        authorEntity.setFullName(pQuestionRequestDTO.getFullName());
        personRepository.save(authorEntity);
        user.setPerson(authorEntity);
        //Guardamos un person ID asociado al user (http session id)
        userService.save(user);
        LectureEntity lectureEntity = lectureRepository.findOne(pQuestionRequestDTO.getLectureId());
        if (lectureEntity == null) {
            throw new NoSuchElementException(String.format("Charla no encontrada: %s ", pQuestionRequestDTO.getLectureId()));
        }
        QuestionResponseDTO responseDTO = insertNewQuestion(pQuestionRequestDTO, authorEntity, lectureEntity);
        return responseDTO;
    }

    private QuestionResponseDTO insertNewQuestion(QuestionRequestDTO pQuestionRequestDTO, PersonEntity pAuthorEntity, LectureEntity pLectureEntity) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setAuthor(pAuthorEntity);
        questionEntity.setTitle(pQuestionRequestDTO.getTitle());
        questionEntity.setLecture(pLectureEntity);
        //FIXME Unificar los campos
        questionEntity.setDetail(pQuestionRequestDTO.getDescription());
        questionsRepository.save(questionEntity);
        QuestionResponseDTO responseDTO = new QuestionResponseDTO();
        BeanUtils.copyProperties(questionEntity, responseDTO);
        LectureResponseDTO lectureResponse = new LectureResponseDTO();
        BeanUtils.copyProperties(pLectureEntity, lectureResponse);
        responseDTO.setLecture(lectureResponse);
        PersonResponseDTO author = new PersonResponseDTO();
        BeanUtils.copyProperties(pAuthorEntity, author);
        responseDTO.setAuthor(author);
        responseDTO.setAuthor(author);
        return responseDTO;
    }
}
