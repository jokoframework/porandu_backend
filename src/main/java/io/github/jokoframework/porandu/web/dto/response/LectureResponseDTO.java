package io.github.jokoframework.porandu.web.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by afeltes on 25/07/16.
 */
public class LectureResponseDTO {

    private Long lectureId;
    private String code;
    private String description;
    private EventResponseDTO event;
    private PersonResponseDTO author;

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long pLectureId) {
        lectureId = pLectureId;
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
                .append("lectureId", lectureId)
                .append("code", code)
                .append("description", description)
                .append("event", event)
                .append("author", author)
                .toString();
    }
}
