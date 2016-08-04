package io.github.jokoframework.porandu.web.dto.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by joko on 24/06/16.
 */
public class QuestionRequestDTO {
    private String id;
    private String title;
    private String description;
    private String email;
    private String fullName;

    public String getId() {
        return id;
    }

    public void setId(String pId) {
        id = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }
    

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	@Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("description", description)
                .toString();
    }
}
