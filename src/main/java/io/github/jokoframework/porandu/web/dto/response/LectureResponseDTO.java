package io.github.jokoframework.porandu.web.dto.response;

import io.github.jokoframework.porandu.dto.BaseResponseDTO;
import io.github.jokoframework.porandu.entities.EventEntity;
import io.github.jokoframework.porandu.entities.PersonEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * Created by afeltes on 25/07/16.
 */
public class LectureResponseDTO {

    private Long id;
    private String code;
    private String description;
    private EventResponseDTO event;
    private PersonResponseDTO author;

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String pCode) {
        code = pCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public EventResponseDTO getEvent() {
        return event;
    }

    public void setEvent(EventResponseDTO pEvent) {
        event = pEvent;
    }

    public PersonResponseDTO getAuthor() {
        return author;
    }

    public void setAuthor(PersonResponseDTO pAuthor) {
        author = pAuthor;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("code", code)
                .append("description", description)
                .append("event", event)
                .append("author", author)
                .toString();
    }
}
