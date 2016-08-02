package io.github.jokoframework.porandu.service;

import io.github.jokoframework.porandu.web.dto.response.EventResponseDTO;

import java.util.List;

/**
 * Created by afeltes on 25/07/16.
 */
public interface EventsService {
    List<EventResponseDTO> findAll();
}
