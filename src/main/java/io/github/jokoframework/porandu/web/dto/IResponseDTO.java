package io.github.jokoframework.porandu.web.dto;

import io.github.jokoframework.porandu.dto.BaseResponseDTO;
import org.springframework.http.HttpStatus;

/**
 * Created by joko on 12/06/16.
 */
public interface IResponseDTO {
    String getMessage();

    BaseResponseDTO setMessage(String pMessage);

    HttpStatus getHttpStatus();

    void setHttpStatus(HttpStatus pHttpStatus);

}
