package io.github.jokoframework.porandu.web.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class QuestionResponseDTO {

    private Long questionId;
    private String title;
    private String detail;
    private LectureResponseDTO lecture;
    private PersonResponseDTO author;
    private Long votes;
    private boolean voted;
    private Date insertedAt;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long pQuestionId) {
        questionId = pQuestionId;
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

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date pInsertedAt) {
        insertedAt = pInsertedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("questionId", questionId)
                .append("title", title)
                .append("detail", detail)
                .append("lecture", lecture)
                .append("author", author)
                .append("votes", votes)
                .append("voted", voted)
                .append("insertedAt", insertedAt)
                .toString();
    }
}
