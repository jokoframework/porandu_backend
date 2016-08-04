package io.github.jokoframework.porandu.service;

import io.github.jokoframework.porandu.web.dto.request.QuestionRequestDTO;
import io.github.jokoframework.porandu.web.dto.response.QuestionResponseDTO;

import java.util.List;

/**
 * Created by afeltes on 25/07/16.
 */
public interface QuestionsService {
    List<QuestionResponseDTO> findByLecture(Long pLectureId);

    QuestionResponseDTO findById(Long questionId);
    QuestionResponseDTO vote(QuestionResponseDTO question, String userName);
    QuestionResponseDTO downvote(QuestionResponseDTO question, String userName);
    List<QuestionResponseDTO> getVotes(List<QuestionResponseDTO> questions, String userName);

    QuestionResponseDTO save(QuestionRequestDTO pQuestionRequestDTO, String pUserName);
}
