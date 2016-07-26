package io.github.jokoframework.porandu.web.controller;

import io.github.jokoframework.porandu.rest.ApiPaths;
import io.github.jokoframework.porandu.web.dto.response.HeartBeatResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by joko on 12/05/16.
 */

public abstract class BaseRestController {

//    @RequestMapping(value = ApiPaths.ROOT_DIAGNOSTIC + "/" + ApiPaths.SUFFIX_HEART_BEAT, method = RequestMethod.GET)
    public abstract ResponseEntity<HeartBeatResponseDTO> getHearbeat();

    public HeartBeatResponseDTO getHeartBeatStatus() {
        HttpStatus httpStatus = HttpStatus.OK;
        HeartBeatResponseDTO heartBeatResponseDTO = new HeartBeatResponseDTO();
        String result = String.format("Service status: %s, date: %s", "OK", new Date());
        heartBeatResponseDTO.setSuccess(true);
        heartBeatResponseDTO.setMessage(result);
        heartBeatResponseDTO.setHttpStatus(httpStatus);
        return heartBeatResponseDTO;
    }

}
