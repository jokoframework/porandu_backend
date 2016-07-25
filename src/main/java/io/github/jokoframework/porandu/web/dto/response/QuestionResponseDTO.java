package io.github.jokoframework.porandu.web.dto.response;

import io.github.jokoframework.porandu.dto.BaseResponseDTO;
import io.github.jokoframework.porandu.entities.LectureEntity;
import io.github.jokoframework.porandu.web.dto.IResponseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.HttpStatus;

import javax.persistence.*;

public class QuestionResponseDTO extends BaseResponseDTO implements IResponseDTO {

    public static final String HTTP_STATUS_ACCEPTED = "ACCEPTED";
    public static final String HTTP_STATUS_UNAUTHORIZED = "UNAUTHORIZED";

    private Long id;
    private String title;
    private String detail;
    private LectureResponseDTO lecture;
    private PersonResponseDTO author;

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String pDetail) {
        detail = pDetail;
    }

    public LectureResponseDTO getLecture() {
        return lecture;
    }

    public void setLecture(LectureResponseDTO pLecture) {
        lecture = pLecture;
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
                .appendSuper(super.toString())
                .append("id", id)
                .append("title", title)
                .append("detail", detail)
                .toString();
    }
}
