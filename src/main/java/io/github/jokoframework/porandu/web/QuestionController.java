package io.github.jokoframework.porandu.web;

import com.wordnik.swagger.annotations.*;
import io.github.jokoframework.porandu.constantes.ApplicationConstants;
import io.github.jokoframework.porandu.rest.ApiPaths;
import io.github.jokoframework.porandu.service.QuestionService;
import io.github.jokoframework.porandu.web.dto.response.QuestionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class QuestionController extends BaseRestController {

    @Autowired
    private QuestionService questionService;

    @ApiOperation(value = "Provee la lista preguntas para la charla seleccionada", position = 1)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se provee la lista de preguntas para la charla."),
            @ApiResponse(code = 401, message = "El usuario introdujo alguna credencial inválida."),
            @ApiResponse(code = 409, message = "Se pasó algún parámetro incorrecto.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name = ApplicationConstants.VERSION_HEADER_NAME, dataType = "String",
            paramType = "header", required = false, value = "Version", defaultValue = "1.0"))
    @RequestMapping(value = ApiPaths.ROOT_QUESTIONS, method = RequestMethod.GET)
    public List<QuestionResponseDTO> listarRubros(HttpServletRequest request,
                                                  @RequestParam(name = "lectureId", required = true) Long lectureId) {
        List<QuestionResponseDTO> byLecture = questionService.findByLecture(lectureId);
        return byLecture;
    }

}
