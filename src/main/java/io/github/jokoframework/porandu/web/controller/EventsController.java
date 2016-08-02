package io.github.jokoframework.porandu.web.controller;

import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.github.jokoframework.porandu.constantes.ApplicationConstants;
import io.github.jokoframework.porandu.rest.ApiPaths;
import io.github.jokoframework.porandu.service.EventsService;
import io.github.jokoframework.porandu.web.dto.response.EventResponseDTO;
import io.github.jokoframework.porandu.web.dto.response.HeartBeatResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class EventsController extends BaseRestController {

    @Autowired
    private EventsService eventService;

    @Override
    @RequestMapping(value = ApiPaths.EVENTS_HEARTBEAT, method = RequestMethod.GET)
    public ResponseEntity<HeartBeatResponseDTO> getHearbeat() {
        HeartBeatResponseDTO heartBeatResponseDTO = getHeartBeatStatus();
        return new ResponseEntity<HeartBeatResponseDTO>(heartBeatResponseDTO, heartBeatResponseDTO.getHttpStatus());
    }

    @ApiOperation(value = "Provee la lista de eventos", position = 1)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se provee la lista de eventos."),
            @ApiResponse(code = 401, message = "El usuario introdujo alguna credencial inválida."),
            @ApiResponse(code = 409, message = "Se pasó algún parámetro incorrecto.")
    })
    @ApiImplicitParams(@ApiImplicitParam(name = ApplicationConstants.VERSION_HEADER_NAME, dataType = "String",
            paramType = "header", required = false, value = "Version", defaultValue = "1.0"))
    @RequestMapping(value = ApiPaths.ROOT_EVENTS, method = RequestMethod.GET)
    public List<EventResponseDTO> getEvents(HttpServletRequest request) {
        List<EventResponseDTO> events= eventService.findAll();
        return events;
    }

}
