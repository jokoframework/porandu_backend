package io.github.jokoframework.porandu.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

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
    private Long id;

    @Basic
    private String title;

    @Basic
    private String detail;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private LectureEntity lecture;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private PersonEntity author;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
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
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("title", title)
                .append("detail", detail)
                .append("lecture", lecture)
                .append("author", author)
                .toString();
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
                .append(this.id, rhs.id)
                .append(this.title, rhs.title)
                .append(this.detail, rhs.detail)
                .append(this.lecture, rhs.lecture)
                .append(this.author, rhs.author)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(title)
                .append(detail)
                .append(lecture)
                .append(author)
                .toHashCode();
    }
}
