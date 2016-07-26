package io.github.jokoframework.porandu.service;

import io.github.jokoframework.porandu.web.dto.response.LectureResponseDTO;

import java.util.List;

/**
 * Created by afeltes on 25/07/16.
 */
public interface LecturesService {
    List<LectureResponseDTO> findByEventId(Long pEventId);
}
