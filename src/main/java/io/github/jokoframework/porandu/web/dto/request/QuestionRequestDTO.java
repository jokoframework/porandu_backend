package io.github.jokoframework.porandu.web.dto.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by joko on 24/06/16.
 */
public class QuestionRequestDTO {
    private String id;
    private String title;
    private String description;

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


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("description", description)
                .toString();
    }
}
