package io.github.jokoframework.porandu.web.dto.response;

import io.github.jokoframework.porandu.dto.BaseResponseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by afeltes on 25/07/16.
 */
public class EventResponseDTO extends BaseResponseDTO {
    private Long id;
    private String code;
    private String description;

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


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("code", code)
                .append("description", description)
                .toString();
    }
}
