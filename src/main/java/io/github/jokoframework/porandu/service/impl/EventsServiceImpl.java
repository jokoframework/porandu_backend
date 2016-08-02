package io.github.jokoframework.porandu.service.impl;

import io.github.jokoframework.porandu.entities.EventEntity;
import io.github.jokoframework.porandu.repositories.EventsRepository;
import io.github.jokoframework.porandu.service.EventsService;
import io.github.jokoframework.porandu.web.dto.response.EventResponseDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author agimenez
 */
@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    private EventsRepository repository;

    @Override
    public List<EventResponseDTO> findAll() {
        List<EventResponseDTO > events = new ArrayList<>();
        List<EventEntity> eventEntities = repository.findAll();
        if (CollectionUtils.isNotEmpty(eventEntities)) {
            for (EventEntity entity : eventEntities) {
                EventResponseDTO event = new EventResponseDTO();
                BeanUtils.copyProperties(entity, event);
                events.add(event);
            }
        }
        return events;
    }
}
