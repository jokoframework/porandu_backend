package io.github.jokoframework.porandu.web.controller;

import com.wordnik.swagger.annotations.*;
import io.github.jokoframework.porandu.constantes.ApplicationConstants;
import io.github.jokoframework.porandu.rest.ApiPaths;
import io.github.jokoframework.porandu.service.LecturesService;
import io.github.jokoframework.porandu.web.dto.response.HeartBeatResponseDTO;
import io.github.jokoframework.porandu.web.dto.response.LectureResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LecturesController extends BaseRestController {

    @Autowired
    private LecturesService lecturesService;

    @Override
    @RequestMapping(value = ApiPaths.ROOT_LECTURES + "/" + ApiPaths.SUFFIX_HEART_BEAT, method = RequestMethod.GET)
    public ResponseEntity<HeartBeatResponseDTO> getHearbeat() {
        HeartBeatResponseDTO heartBeatResponseDTO = getHeartBeatStatus();
        return new ResponseEntity<HeartBeatResponseDTO>(heartBeatResponseDTO, heartBeatResponseDTO.getHttpStatus());
    }

    @ApiOperation(value = "Provee la lista charlas para el evento seleccionado", position = 1)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se provee la lista de charlas para el evento."),
            @ApiResponse(code = 401, message = "El usuario introdujo alguna credencial inválida."),
            @ApiResponse(code = 409, message = "Se pasó algún parámetro incorrecto.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name = ApplicationConstants.VERSION_HEADER_NAME, dataType = "String",
            paramType = "header", required = false, value = "Version", defaultValue = "1.0"))
    @RequestMapping(value = ApiPaths.ROOT_LECTURES, method = RequestMethod.GET)
    public List<LectureResponseDTO> listarRubros(HttpServletRequest request,
                                                 @RequestParam(name = "eventId", required = true) Long eventId) {
        List<LectureResponseDTO> byLecture = lecturesService.findByEventId(eventId);
        return byLecture;
    }

}
