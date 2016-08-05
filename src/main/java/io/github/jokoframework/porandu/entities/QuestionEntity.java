package io.github.jokoframework.porandu.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by afeltes on 25/07/16.
 */
@Entity
@Table(name = "questions", schema = "porandu")
@SequenceGenerator(name = "question_id_seq", sequenceName = "porandu.question_id_seq", schema = "porandu", initialValue = 1, allocationSize = 1)
public class QuestionEntity extends BaseEntity {

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "question_id_seq")
    @Column(name = "question_id")
    private Long questionId;

    @Basic
    private String title;

    @Basic
    @Column(length = 4096)
    private String detail;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private LectureEntity lecture;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private PersonEntity author;

    @Column(name = "inserted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertedAt;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long pId) {
        questionId = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pCode) {
        title = pCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String pDescription) {
        detail = pDescription;
    }

    public LectureEntity getLecture() {
        return lecture;
    }

    public void setLecture(LectureEntity pLecture) {
        lecture = pLecture;
    }

    public PersonEntity getAuthor() {
        return author;
    }

    public void setAuthor(PersonEntity pAuthor) {
        author = pAuthor;
    }

    @Override
    public Long getId() {
        return questionId;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date pInsertedAt) {
        insertedAt = pInsertedAt;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        QuestionEntity rhs = (QuestionEntity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.questionId, rhs.questionId)
                .append(this.title, rhs.title)
                .append(this.detail, rhs.detail)
                .append(this.lecture, rhs.lecture)
                .append(this.author, rhs.author)
                .append(this.insertedAt, rhs.insertedAt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(questionId)
                .append(title)
                .append(detail)
                .append(lecture)
                .append(author)
                .append(insertedAt)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("questionId", questionId)
                .append("title", title)
                .append("detail", detail)
                .append("lecture", lecture)
                .append("author", author)
                .append("insertedAt", insertedAt)
                .toString();
    }

    @PrePersist
    public void setDefaults() {
        if(getInsertedAt() == null) {
            setInsertedAt(new Date());
        }
    }
}
