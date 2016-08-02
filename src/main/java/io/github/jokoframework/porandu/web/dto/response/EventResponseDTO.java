package io.github.jokoframework.porandu.web.dto.response;

import io.github.jokoframework.porandu.dto.BaseResponseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by afeltes on 25/07/16.
 */
public class EventResponseDTO {
    private Long eventId;
    private String code;
    private String description;
    private String imageUrl;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long pEventId) {
        eventId = pEventId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String pImageUrl) {
        imageUrl = pImageUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("eventId", eventId)
                .append("code", code)
                .append("description", description)
                .append("imageUrl", imageUrl)
                .toString();
    }
}
