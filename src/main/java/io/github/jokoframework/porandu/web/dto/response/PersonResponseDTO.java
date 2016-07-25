package io.github.jokoframework.porandu.web.dto.response;

import io.github.jokoframework.porandu.dto.BaseResponseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by afeltes on 25/07/16.
 */
public class PersonResponseDTO {
    private Long id;
    private String fullName;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String pFullName) {
        fullName = pFullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String pEmail) {
        email = pEmail;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("fullName", fullName)
                .append("email", email)
                .toString();
    }
}
