package io.github.jokoframework.porandu.web.controller;

import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.github.jokoframework.porandu.constantes.ApplicationConstants;
import io.github.jokoframework.porandu.rest.ApiPaths;
import io.github.jokoframework.porandu.service.QuestionsService;
import io.github.jokoframework.porandu.web.dto.response.HeartBeatResponseDTO;
import io.github.jokoframework.porandu.web.dto.response.QuestionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class QuestionsController extends BaseRestController {

    @Autowired
    private QuestionsService questionService;


    @Override
    @RequestMapping(value = ApiPaths.QUESTIONS_HEARTBEAT, method = RequestMethod.GET)
    public ResponseEntity<HeartBeatResponseDTO> getHearbeat() {
        HeartBeatResponseDTO heartBeatResponseDTO = getHeartBeatStatus();
        return new ResponseEntity<HeartBeatResponseDTO>(heartBeatResponseDTO, heartBeatResponseDTO.getHttpStatus());
    }

    @ApiOperation(value = "Provee la lista preguntas para la charla seleccionada", position = 1)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se provee la lista de preguntas para la charla."),
            @ApiResponse(code = 401, message = "El usuario introdujo alguna credencial inválida."),
            @ApiResponse(code = 409, message = "Se pasó algún parámetro incorrecto.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name = ApplicationConstants.VERSION_HEADER_NAME, dataType = "String",
            paramType = "header", required = false, value = "Version", defaultValue = "1.0"))
    @RequestMapping(value = ApiPaths.ROOT_QUESTIONS, method = RequestMethod.GET)
    public List<QuestionResponseDTO> getQuestionsByLecture(HttpServletRequest request,
                                                  @RequestParam(name = "lectureId", required = true) Long lectureId) {
        HttpSession session = request.getSession();
        List<QuestionResponseDTO> byLecture = questionService.findByLecture(lectureId);
        return questionService.getVotes(byLecture, session.getId());
    }

    @ApiOperation(value = "Votar una pregunta", position = 2)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El usuario realiza el voto."),
            @ApiResponse(code = 401, message = "El usuario introdujo alguna credencial inválida."),
            @ApiResponse(code = 409, message = "Se pasó algún parámetro incorrecto.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name = ApplicationConstants.VERSION_HEADER_NAME, dataType = "String",
            paramType = "header", required = false, value = "Version", defaultValue = "1.0"))
    @RequestMapping(value = ApiPaths.QUESTION_VOTE, method = RequestMethod.POST)
    public QuestionResponseDTO vote(HttpServletRequest request, HttpSession session,
            @PathVariable(value = "questionId") Long questionId) {
        QuestionResponseDTO question = questionService.findById(questionId);
        return questionService.vote(question, session.getId());
    }

    @ApiOperation(value = "Desvotar una pregunta", position = 2)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El usuario recupera su voto."),
            @ApiResponse(code = 401, message = "El usuario introdujo alguna credencial inválida."),
            @ApiResponse(code = 409, message = "Se pasó algún parámetro incorrecto.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name = ApplicationConstants.VERSION_HEADER_NAME, dataType = "String",
            paramType = "header", required = false, value = "Version", defaultValue = "1.0"))
    @RequestMapping(value = ApiPaths.QUESTION_DOWNVOTE, method = RequestMethod.POST)
    public QuestionResponseDTO dowvote(HttpServletRequest request, HttpSession session,
            @PathVariable(value = "questionId") Long questionId) {;
        QuestionResponseDTO question = questionService.findById(questionId);
        return questionService.downvote(question, session.getId());
    }

}
