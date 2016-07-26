package io.github.jokoframework.porandu.web.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by afeltes on 25/07/16.
 */
public class PersonResponseDTO {
    private Long personId;
    private String fullName;
    private String email;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long pPersonId) {
        personId = pPersonId;
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
                .append("personId", personId)
                .append("fullName", fullName)
                .append("email", email)
                .toString();
    }
}
